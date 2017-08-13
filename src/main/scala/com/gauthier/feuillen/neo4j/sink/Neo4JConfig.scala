package com.gauthier.feuillen.neo4j.sink

import org.neo4j.driver.v1.{AuthTokens, Config, Driver, GraphDatabase}

import scala.collection.mutable

class Neo4JConfig (val url:String, val username:String="neo4j", val password:Option[String]=None){

  //Set config encryption to none when possible
  private val config = Config.build().withoutEncryption().toConfig

  def driver():Driver=
       this.password match{
        case Some(s:String) => GraphDatabase.driver(url, AuthTokens.basic(username, s), config)
        case _ => GraphDatabase.driver(url,config)
      }
}

object Neo4JConfig{
  val prefix = "neo4j.bolt."
  def apply(map: mutable.Map[String,String]):Neo4JConfig={
    new Neo4JConfig(
      map.getOrElse(prefix+"url", "bolt://localhost"),
      map.getOrElse(prefix+"username", "neo4j"),
      map.get(prefix+"password")
    )
  }
}
