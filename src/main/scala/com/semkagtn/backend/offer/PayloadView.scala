package com.semkagtn.backend.offer

final case class PayloadView(mark: Option[String] = None,
                             model: Option[String] = None,
                             rooms: Option[Int] = None,
                             floor: Option[Int] = None) {

  def asModel: Either[IllegalArgumentException, Payload] =
    (mark, model, rooms, floor) match {
      case (Some(mark), Some(model), None, None) =>
        Right(Payload.Car(mark, model))
      case (None, None, Some(rooms), Some(floor)) =>
        Right(Payload.Flat(rooms, floor))
      case _ =>
        Left(new IllegalArgumentException(s"Invalid item view: $this"))
    }
}

object PayloadView {

  def asView(model: Payload): PayloadView = model match {
    case Payload.Car(mark, model) =>
      PayloadView(
        mark = Some(mark),
        model = Some(model)
      )
    case Payload.Flat(rooms, floor) =>
      PayloadView(
        rooms = Some(rooms),
        floor = Some(floor)
      )
  }
}
