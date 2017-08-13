package com.gfeuillen.neo4j.util

import java.util.Properties

object ConnectorProperties {

  val prefix = "connector."

  val properties:Properties = new Properties();
  properties.load(this.getClass().getResourceAsStream("connector.properties"));

  def version:String = properties.getProperty(prefix+"version")
}
