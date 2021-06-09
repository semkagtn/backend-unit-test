package com.semkagtn.backend.http

import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.semkagtn.backend.SpecBase
import org.scalamock.scalatest.MockFactory

trait ControllerSpecBase
  extends SpecBase
    with MockFactory
    with ScalatestRouteTest
    with Directives {

  def controller: ControllerBase
}
