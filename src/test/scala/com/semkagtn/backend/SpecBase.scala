package com.semkagtn.backend

import com.semkagtn.backend.SpecBase.RichGen
import org.scalacheck.Gen
import org.scalatest.Assertion
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.Matcher
import org.scalatest.matchers.should.Matchers

trait SpecBase
  extends AnyFunSpec
    with Matchers
    with MatcherSupport
    with SoftAssertSupport
    with ParameterizedSupport
    with PropertyBaseSupport {

  def assertThat[A, E <: A](actualResult: A, expectedResult: Matcher[E]): Assertion =
    actualResult should expectedResult.asInstanceOf[Matcher[A]]

  implicit def gen2RichGen[A](gen: Gen[A]): RichGen[A] =
    new RichGen(gen)
}

object SpecBase {

  class RichGen[A](val gen: Gen[A])
    extends AnyVal {

    def next: A =
      LazyList.continually(gen.sample).flatten.head
  }
}
