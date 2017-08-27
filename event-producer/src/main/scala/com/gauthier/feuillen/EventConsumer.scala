package com.gauthier.feuillen

import java.util
import java.util.Properties
import collection.JavaConversions._

import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}

object EventConsumer {
  def main(args: Array[String]): Unit = {
    val props = new Properties()
    props.put("group.id", "test2")
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("bootstrap.servers", "192.168.1.32:9092")
    props.put("acks", "all")
    props.put("retries", 0.toString)
    props.put("batch.size", 16384.toString)
    props.put("linger.ms", 1.toString)
    props.put("buffer.memory", 33554432.toString)
    props.put("auto.offset.reset", "earliest")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(util.Arrays.asList("nodes"))
    while (true) {
      val records:ConsumerRecords[String, String] = consumer.poll(100)
      for (record:ConsumerRecord[String,String] <- records){
        println(record.key())
        println(record.value())
      }
    }
  }
}
