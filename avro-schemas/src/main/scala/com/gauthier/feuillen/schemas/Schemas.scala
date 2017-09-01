package com.gauthier.feuillen.schemas

import com.sksamuel.avro4s.{FromRecord, RecordFormat, SchemaFor, ToRecord}
import io.confluent.connect.avro.AvroData
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.connect.connector.ConnectRecord


case class Node(id:String, nodeType:String)

object Schemas {
  def valueToGenericRecord[T <: ConnectRecord[T]](record: ConnectRecord[T]): GenericRecord = {
    val avroData = new AvroData(100)
    val avro = avroData.fromConnectData(record.valueSchema(), record.value())
    avro.asInstanceOf[GenericRecord]
  }

  def valueToClass[T: SchemaFor : ToRecord: FromRecord, V <: ConnectRecord[V]](record : ConnectRecord[V]) :T={
    val recordFormat = RecordFormat[T]
    val genericRecord = valueToGenericRecord(record)
    recordFormat.from(genericRecord)
  }

  def classToGenericRecord[T: SchemaFor : ToRecord: FromRecord](c:T):GenericRecord={
    val recordFormat = RecordFormat[T]
    recordFormat.to(c)
  }
}
