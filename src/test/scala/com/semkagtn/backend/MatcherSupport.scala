package com.semkagtn.backend

import org.scalatest.matchers.Matcher

trait MatcherSupport {

  // TODO generalize
  def greaterThan(value: Long): Matcher[Long] = new ScalatestMatcher[Long] {

    override protected def matches(actualResult: Long): Boolean =
      actualResult > value

    override protected def asString: String =
      s"greater than $value"
  }

  def equalTo[A](expected: A): Matcher[A] = new ScalatestMatcher[A] {
    override protected def matches(actualResult: A): Boolean =
      actualResult == expected

    override protected def asString: String =
      expected.toString
  }
}
