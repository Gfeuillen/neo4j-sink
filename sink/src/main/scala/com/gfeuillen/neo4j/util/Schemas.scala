package com.gfeuillen.neo4j.util

import com.sksamuel.avro4s.{AvroSchema, RecordFormat}
import io.confluent.connect.avro.AvroData
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.connect.connector.ConnectRecord


case class Node(id:String, nodeType:String)


object Schemas {

  val nodeRecordFormat = RecordFormat[Node]

  def valueToGenericRecord[T <: ConnectRecord[T]](record: ConnectRecord[T]): GenericRecord = {
    val avroData = new AvroData(100)
    val avro = avroData.fromConnectData(record.valueSchema(), record.value())
    avro.asInstanceOf[GenericRecord]
  }
}
