package com.semkagtn.backend.offer

import com.semkagtn.backend.user.UserId

class InMemoryOfferDao
  extends OfferDao {

  private var map = Map.empty[UserId, Set[Offer]]

  override def get(id: OfferId): Option[Offer] =
    map.flatMap(_._2).find(_.id == id)

  override def getAll(userId: UserId): Set[Offer] = synchronized {
    map.getOrElse(userId, Set.empty)
  }

  override def put(userId: UserId, offer: Offer): Unit = synchronized {
    val resultOffers = map.getOrElse(userId, Set.empty).filter(_.id != offer.id) + offer
    map = map.updated(userId, resultOffers)
  }

  override def delete(id: OfferId): Boolean = synchronized {
    val offersBefore = map.flatMap(_._2).size
    map = map.view.mapValues(_.filter(_.id != id)).toMap
    map.flatMap(_._2).size != offersBefore
  }
}
