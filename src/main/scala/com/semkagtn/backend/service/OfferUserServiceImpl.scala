package com.semkagtn.backend.service

import com.semkagtn.backend.offer.{Offer, OfferDao, OfferId}
import com.semkagtn.backend.user.{User, UserDao, UserId, UserWithOffers}
import com.semkagtn.backend.util.IdGenerator

class OfferUserServiceImpl(userDao: UserDao,
                           offerDao: OfferDao,
                           idGenerator: IdGenerator)
  extends OfferUserService {

  override def createUser(request: UserCreateRequest): UserWithOffers = {
    val UserCreateRequest(name) = request
    val user = User(
      id = idGenerator.newId(),
      name = name
    )
    userDao.put(user)
    UserWithOffers(user, Set.empty)
  }

  override def getUser(userId: UserId): Option[UserWithOffers] =
    userDao.get(userId).map { user =>
      val offers = offerDao.getAll(userId)
      UserWithOffers(user, offers)
    }

  override def createOffer(request: OfferCreateRequest): Offer = {
    val OfferCreateRequest(userId, title, price, payload) = request
    if (userDao.get(userId).isEmpty) {
      throw new IllegalArgumentException(s"User $userId doesn't exist")
    }
    val offer = Offer(
      id = idGenerator.newId(),
      title = title,
      price = price,
      payload = payload
    )
    offerDao.put(userId, offer)
    offer
  }

  override def getOffer(offerId: OfferId): Option[Offer] =
    offerDao.get(offerId)

  override def deleteUser(userId: UserId): Unit = {
    val offerIds = offerDao.getAll(userId).map(_.id)
    userDao.delete(userId)
    offerIds.foreach(offerDao.delete)
  }
}
