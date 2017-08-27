package com.gauthier.feuillen

import com.sksamuel.avro4s.{AvroSchema, RecordFormat}


case class Node(id:String, nodeType:String)


object Schemas {

  val nodeRecordFormat = RecordFormat[Node]
  def main(args: Array[String]): Unit = {
    val schema = AvroSchema[Node]

    println(schema)
  }
}
