package com.semkagtn.backend

import org.scalatest.matchers.{MatchResult, Matcher}

trait ScalatestMatcher[-A]
  extends Matcher[A] {

  final override def apply(actualResult: A): MatchResult = {
    val expectedResult = asString
    MatchResult(
      matches = matches(actualResult),
      rawFailureMessage = s"'$actualResult' was not '$expectedResult'",
      rawNegatedFailureMessage = s"'$actualResult' was '$expectedResult'"
    )
  }

  final override def toString: String =
    asString

  protected def matches(actualResult: A): Boolean

  protected def asString: String
}
