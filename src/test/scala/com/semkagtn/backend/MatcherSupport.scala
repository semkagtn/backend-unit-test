package com.semkagtn.backend

import org.scalatest.matchers.Matcher

trait MatcherSupport {

  def equalTo[A](expected: A): Matcher[A] = new ScalatestMatcher[A] {
    override protected def matches(actualResult: A): Boolean =
      actualResult == expected

    override protected def asString: String =
      expected.toString
  }

  def approxEqual(expected: Double): Matcher[Double] = new ScalatestMatcher[Double] {
    override protected def matches(actualResult: Double): Boolean =
      Math.abs(expected - actualResult) < 1e-6

    override protected def asString: String =
      s"approximately equal to $expected"
  }

  def isNan: Matcher[Double] = new ScalatestMatcher[Double] {
    override protected def matches(actualResult: Double): Boolean = actualResult.isNaN

    override protected def asString: String = "NaN"
  }

  def isPosInf: Matcher[Double] = new ScalatestMatcher[Double] {
    override protected def matches(actualResult: Double): Boolean = actualResult.isPosInfinity

    override protected def asString: String = "Infinity"
  }
}
