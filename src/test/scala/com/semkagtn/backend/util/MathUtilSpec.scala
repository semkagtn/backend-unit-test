package com.semkagtn.backend.util

import com.semkagtn.backend.SpecBase
import org.scalatest.matchers.Matcher

class MathUtilSpec
  extends SpecBase {

  case class P(base: Int, exponent: Int, expectedResult: Matcher[Double])

  private val params: Seq[(String, P)] = Seq(
    "zero to zero" -> P(0, 0, isNan),
    "zero to positive" -> P(0, 22, approxEqual(0.0)),
    "zero to negative" -> P(0, -1, isPosInf),
    "positive to zero" -> P(2, 0, approxEqual(1.0)),
    "negative to zero" -> P(-2, 0, approxEqual(1.0)),
    "negative to odd" -> P(-2, 3, approxEqual(-8.0)),
    "negative to even" -> P(-2, 2, approxEqual(4.0)),
    "positive to even" -> P(3, 4, approxEqual(81.0)),
    "positive to odd" -> P(4, 3, approxEqual(64.0)),
    "positive to negative" -> P(2, -1, approxEqual(0.5)),
    "negative to negative" -> P(-2, -1, approxEqual(-0.5))
  )

  parameterized(params) { case P(base, n, expectedResult) =>
    val actual = MathUtil.pow(base, n)
    assertThat(actual, expectedResult)
  }
}
