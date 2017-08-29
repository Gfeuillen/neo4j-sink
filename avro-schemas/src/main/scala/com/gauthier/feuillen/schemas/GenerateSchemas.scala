package com.gauthier.feuillen.schemas

import org.rogach.scallop.ScallopConf


class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  val apples = opt[Int](required = true)
  val bananas = opt[Int]()
  val name = trailArg[String]()
  verify()
}

object GenerateSchemas {
  def main(args: Array[String]): Unit = {
      val nodeSchema =
  }
}
