package com.gfeuillen.neo4j.sink

import com.gfeuillen.neo4j.util.{ConnectorProperties, Neo4JConfig, Schemas}
import com.gfeuillen.neo4j.wrapper.ScalaSinkTask
import org.apache.kafka.connect.sink.SinkRecord
import org.neo4j.driver.v1.{Driver, Session}
import io.confluent.connect.avro.{AvroConverter, AvroData}
import org.apache.avro.generic.GenericRecord

import scala.collection.mutable

class Neo4JSinkTask extends ScalaSinkTask{

  var driver:Option[Driver] = None
  var session:Option[Session] = None

  override def start(map: mutable.Map[String, String]): Unit = {
    val neo4JConfig = Neo4JConfig(map)
    driver = Some(neo4JConfig.driver())
    session = driver match{
      case Some(driver:Driver) => Some(driver.session())
      case _ => None
    }
  }

  override def put(records: Iterable[SinkRecord]): Unit = {
    println(records.size)
    records.foreach(sk =>
      if ((sk.key() != null) && (sk.value() != null)) {
        val avro = new AvroData(100).fromConnectData(sk.valueSchema(), sk.value())
        val genericData = avro.asInstanceOf[GenericRecord]
        val node = Schemas.nodeRecordFormat.from(genericData)

        println(node.id)
        println(node.nodeType)
      }
    )
  }

  override def stop(): Unit = {
    session match{
      case Some(session) => session.close()
      case _ => Unit
    }
    driver match{
      case Some(driver) => driver.close()
      case _ => Unit
    }
  }

  override def version(): String = ConnectorProperties.version
}
