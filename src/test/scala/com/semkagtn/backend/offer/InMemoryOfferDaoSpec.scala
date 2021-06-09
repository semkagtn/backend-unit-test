package com.semkagtn.backend.offer

class InMemoryOfferDaoSpec
  extends OfferDaoSpecBase {

  override val offerDao: OfferDao = new InMemoryOfferDao
}
