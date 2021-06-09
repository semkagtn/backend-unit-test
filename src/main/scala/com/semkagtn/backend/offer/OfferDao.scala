package com.semkagtn.backend.offer

import com.semkagtn.backend.user.UserId

trait OfferDao {

  def get(id: OfferId): Option[Offer]

  def getAll(userId: UserId): Set[Offer]

  def put(userId: UserId, offer: Offer): Unit

  def delete(id: OfferId): Boolean
}
