package com.gauthier.feuillen

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}


object EventProducer {
  def main(args: Array[String]): Unit = {

    val props = new Properties()
    props.put("bootstrap.servers", "192.168.1.32:9092")
    //props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("schema.registry.url", "http://localhost:8081")

    val producer = new KafkaProducer[String,Any](props)

    val testNode = Node("1", "test-node")
    val record = Schemas.nodeRecordFormat.to(testNode)




    producer.send(new ProducerRecord[String, Any]("nodes", testNode.id,record))

    producer.close()
  }
}
