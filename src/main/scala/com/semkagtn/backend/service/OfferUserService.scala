package com.semkagtn.backend.service

import com.semkagtn.backend.offer.{Offer, OfferId}
import com.semkagtn.backend.user.{UserId, UserWithOffers}

trait OfferUserService {

  def createUser(request: UserCreateRequest): UserWithOffers

  def getUser(userId: UserId): Option[UserWithOffers]

  def createOffer(request: OfferCreateRequest): Offer

  def getOffer(offerId: OfferId): Option[Offer]

  def deleteUser(userId: UserId): Unit

  // ... other methods
}
