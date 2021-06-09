package com.semkagtn.backend

import com.semkagtn.backend.offer.{Offer, OfferId, Payload}
import com.semkagtn.backend.service.{OfferCreateRequest, UserCreateRequest}
import com.semkagtn.backend.user.{User, UserId, UserWithOffers}
import com.semkagtn.backend.util.IdGenerator
import org.scalacheck.Gen

object Generators {

  private val idGenerator = new IdGenerator

  val userId: Gen[UserId] =
    Gen.resultOf[Unit, UserId] { _ =>
      idGenerator.newId()
    }

  val offerId: Gen[OfferId] =
    userId

  val user: Gen[User] =
    for {
      id <- Generators.userId
      name <- Gen.alphaNumStr
    } yield User(
      id = id,
      name = name
    )

  val car: Gen[Payload.Car] =
    for {
      mark <- Gen.alphaNumStr
      model <- Gen.alphaNumStr
    } yield Payload.Car(
      mark = mark,
      model = model
    )

  val flat: Gen[Payload.Flat] =
    for {
      rooms <- Gen.choose(1, 7)
      floor <- Gen.choose(1, 100)
    } yield Payload.Flat(
      rooms = rooms,
      floor = floor
    )

  val payload: Gen[Payload] =
    Gen.oneOf(car, flat)

  val offer: Gen[Offer] =
    for {
      id <- offerId
      title <- Gen.alphaNumStr
      price <- Gen.choose(1, 10000)
      payload <- Generators.payload
    } yield Offer(
      id = id,
      title = title,
      price = BigDecimal(price),
      payload = payload
    )

  val userCreateRequest: Gen[UserCreateRequest] =
    for {
      name <- Gen.alphaNumStr
    } yield UserCreateRequest(
      name = name
    )

  val offerCreateRequest: Gen[OfferCreateRequest] =
    for {
      userId <- Generators.userId
      title <- Gen.alphaNumStr
      price <- Gen.choose(1, 10000)
      payload <- Generators.payload
    } yield OfferCreateRequest(
      userId = userId,
      title = title,
      price = BigDecimal(price),
      payload = payload
    )

  val userWithOffers: Gen[UserWithOffers] =
    for {
      user <- Generators.user
      n <- Gen.choose(0, 2)
      offers <- Gen.listOfN(n, Generators.offer)
    } yield UserWithOffers(
      user = user,
      offers = offers.toSet
    )
}
