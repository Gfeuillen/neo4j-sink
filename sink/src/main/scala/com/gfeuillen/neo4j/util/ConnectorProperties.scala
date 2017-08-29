package com.gfeuillen.neo4j.util

import java.util.Properties

object ConnectorProperties {

  val prefix = "connector."

  val properties:Properties = new Properties();
  properties.load(getClass.getResourceAsStream("/connector.properties"));

  def version:String = properties.getProperty(prefix+"version")

  def main(args: Array[String]): Unit = {
    println(ConnectorProperties.version)
  }

}
