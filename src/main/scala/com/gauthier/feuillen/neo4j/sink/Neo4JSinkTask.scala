package com.gauthier.feuillen.neo4j.sink

import com.gauthier.feuillen.neo4j.wrapper.ScalaSinkTask
import org.apache.kafka.connect.sink.SinkRecord

import scala.collection.mutable

class Neo4JSinkTask extends ScalaSinkTask{
  override def start(map: mutable.Map[String, String]): Unit = ???

  override def put(collection: Iterable[SinkRecord]): Unit = ???

  override def stop(): Unit = ???

  override def version(): String = ???
}
