package com.gauthier.feuillen

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

case class Node(id:String, nodeType:String)

object EventProducer {
  def main(args: Array[String]): Unit = {

    val props = new Properties()
    props.put("bootstrap.servers", "192.168.1.32:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    for (i <- 0 until 100) {
      println("here")
      producer.send(new ProducerRecord[String, String]("nodes", Integer.toString(i), Integer.toString(i)))
    }
    producer.close()
  }
}
