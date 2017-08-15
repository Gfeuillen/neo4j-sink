package com.gfeuillen.neo4j.wrapper

import java.util

import org.apache.kafka.connect.sink.SinkConnector

import scala.collection.JavaConverters._
import scala.collection.mutable

trait ScalaSinkConnector extends SinkConnector{

  def start(map:mutable.Map[String,String]):Unit
  override def start(map: util.Map[String, String]) = {
    start(map.asScala)
  }


  def sTaskConfigs(i:Int):Seq[mutable.Map[String,String]]
  override def taskConfigs(i: Int):java.util.List[java.util.Map[String,String]]={
    sTaskConfigs(i).map(_.asJava).asJava
  }

}
