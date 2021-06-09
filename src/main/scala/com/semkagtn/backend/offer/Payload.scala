package com.semkagtn.backend.offer

sealed trait Payload

object Payload {

  final case class Car(mark: String,
                       model: String)
    extends Payload

  final case class Flat(rooms: Int,
                        floor: Int)
    extends Payload

  // ...
}
