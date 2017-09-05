package com.gauthier.feuillen

import java.util
import java.util.Properties

import com.sksamuel.avro4s.RecordFormat
import org.apache.avro.generic.GenericRecord

import collection.JavaConversions._
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}

object EventConsumer {
  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "192.168.1.32:9092")
    props.put("zookeeper.connect", "localhost:2181")
    props.put("group.id", "group1")
    props.put("auto.offset.reset", "earliest")
    props.put("schema.registry.url", "http://localhost:8081")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer")
    val consumer = new KafkaConsumer[String, GenericRecord](props)

    consumer.subscribe(util.Arrays.asList("nodes"))
    while (true) {
      val records:ConsumerRecords[String, GenericRecord] = consumer.poll(100)
      for (record:ConsumerRecord[String, GenericRecord] <-records){
        println(record.key())
        println(record.value())
        println(record.value().getClass.getName)
        val node = Schemas.nodeRecordFormat.from(record.value())
        println(node.id)
        println(node.nodeType)
      }
    }
  }
}
