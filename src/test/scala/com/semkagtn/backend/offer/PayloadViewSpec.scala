package com.semkagtn.backend.offer

import com.semkagtn.backend.{Generators, SpecBase}
import org.scalactic.anyvals.PosInt

class PayloadViewSpec
  extends SpecBase {

  propertyCheck(Generators.payload, PosInt(1000)) { payload =>
    val actualResult = PayloadView.asView(payload).asModel
    val expectedResult = Right(payload)
    actualResult shouldBe expectedResult
  }

  private val negativeCaseParams = Seq(
    "no field" -> PayloadView(
      mark = Some("mark")
    ),
    "extra field" -> PayloadView(
      mark = Some("mark"),
      model = Some("model"),
      rooms = Some(1)
    )
  )

  parameterized(negativeCaseParams) { view =>
    assertThat(view.asModel.isLeft, equalTo(true))
  }
}
