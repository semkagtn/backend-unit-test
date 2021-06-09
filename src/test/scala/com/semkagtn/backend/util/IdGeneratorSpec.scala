package com.semkagtn.backend.util

import com.semkagtn.backend.SpecBase

class IdGeneratorSpec
  extends SpecBase {

  private val idGenerator = new IdGenerator

  describe("newId") {

    it("next id is greater than the previous id") {
      val prevId = idGenerator.newId()
      val nextId = idGenerator.newId()
      assertThat(nextId, greaterThan(prevId))
    }
  }
}
