package com.gfeuillen.neo4j.sink

import java.util.Properties

import com.gfeuillen.neo4j.util.ConnectorProperties
import com.gfeuillen.neo4j.wrapper.ScalaSinkConnector
import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.connect.connector.Task

import scala.collection.mutable

class Neo4JSinkConnector extends ScalaSinkConnector{

  val properties:mutable.Map[String,String] = mutable.Map.empty

  override def start(map: mutable.Map[String, String]): Unit ={
    properties ++= map
  }

  override def sTaskConfigs(i: Int): Seq[mutable.Map[String, String]] = (1 to i).map(n => properties.clone() + ("task.number" ->  n.toString))

  override def taskClass(): Class[_ <: Task] = classOf[Neo4JSinkTask]

  override def version(): String = ConnectorProperties.version

  override def stop(): Unit = ()

  override def config(): ConfigDef = {
    new ConfigDef()
  }
}

