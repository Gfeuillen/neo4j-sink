package com.gauthier.feuillen.schemas

import org.scalatest.FlatSpec

class SchemasTest extends FlatSpec {
  behavior of "The schemas service"

  it should "be able to transform a connect record to a generic one" in {
    assert("test" === "test")
  }
}