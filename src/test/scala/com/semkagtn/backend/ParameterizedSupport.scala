package com.semkagtn.backend

import org.scalatest.Assertion
import org.scalatest.funspec.AnyFunSpecLike

trait ParameterizedSupport { self: AnyFunSpecLike =>

  def parameterized[P](nameParams: Seq[(String, P)])
                      (testCase: P => Assertion): Unit =
    nameParams.foreach { case (name, params) =>
      it(name) {
        testCase(params)
      }
    }
}
