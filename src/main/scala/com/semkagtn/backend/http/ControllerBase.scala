package com.semkagtn.backend.http

import akka.http.scaladsl.marshalling.{PredefinedToEntityMarshallers, ToEntityMarshaller}
import akka.http.scaladsl.server.{Directives, Route}

trait ControllerBase
  extends Directives
    with PredefinedToEntityMarshallers {

  def route: Route

  implicit def anyToRoute[A](f: => A)
                            (implicit m: ToEntityMarshaller[A]): Route =
    complete(f)
}
