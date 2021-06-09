package com.semkagtn.backend

import org.scalacheck.{Arbitrary, Gen, ShrinkLowPriority}
import org.scalactic.anyvals.PosInt
import org.scalatest.Assertion
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

trait PropertyBaseSupport
  extends ScalaCheckDrivenPropertyChecks
    with ShrinkLowPriority {

  override final val generatorDrivenConfig: PropertyCheckConfiguration =
    PropertyCheckConfiguration()

  protected final def propertyCheck[A](gen: Gen[A], count: PosInt)
                                      (property: A => Assertion): Assertion = {
    implicit val arbitrary: Arbitrary[A] = Arbitrary(gen)
    implicit val config: PropertyCheckConfiguration = PropertyCheckConfiguration(minSuccessful = count)
    forAll(property)
  }
}
