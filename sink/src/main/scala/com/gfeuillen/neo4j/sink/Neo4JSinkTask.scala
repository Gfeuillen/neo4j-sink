package com.gfeuillen.neo4j.sink

import com.gauthier.feuillen.schemas.{Node, Schemas}
import com.gfeuillen.neo4j.util.{ConnectorProperties, Neo4JConfig}
import com.gfeuillen.neo4j.wrapper.ScalaSinkTask
import org.apache.kafka.connect.sink.SinkRecord
import org.neo4j.driver.v1.{Driver, Session}

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

    println(session)
  }

  override def put(records: Iterable[SinkRecord]): Unit = {
    println(records.size)
    records.foreach(sk =>
      if ((sk.key() != null) && (sk.value() != null)) {
        val node = Schemas.valueToClass[Node, SinkRecord](sk)
        println(node.id)
        println(node.nodeType)
        println(session)
        val statement = s"MERGE (:${node.nodeType} {id:${node.id}})"
        println(statement)
        runStatement(statement)
      }
    )
  }

  def runStatement(statement:String):Unit={
    session match{
      case Some(s:Session) => {
        val r = s.run(statement)
        println(r)
      }
      case _ => println("No Session Found")
    }
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
