package com.gfeuillen.neo4j.wrapper

import java.util

import org.apache.kafka.connect.sink.{SinkRecord, SinkTask}

import scala.collection.JavaConverters._
import scala.collection.mutable

trait ScalaSinkTask extends SinkTask{

  def start(map:mutable.Map[String,String]):Unit
  override def start(map: util.Map[String, String])={
    start(map.asScala)
  }

  def put(collection:Iterable[SinkRecord])
  override def put(collection: util.Collection[SinkRecord])={
    put(collection.asScala)
  }
}
