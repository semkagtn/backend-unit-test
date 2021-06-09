package com.semkagtn.backend.offer

import com.semkagtn.backend.{Generators, SpecBase}

trait OfferDaoSpecBase
  extends SpecBase {

  def offerDao: OfferDao

  describe("get") {

    it("existent") {
      val userId = Generators.userId.next
      val offer = Generators.offer.next
      offerDao.put(userId, offer)

      val actualResult = offerDao.get(offer.id)
      val expectedResult = Some(offer)
      assertThat(actualResult, equalTo(expectedResult))
    }

    it("nonexistent") {
      val offerId = Generators.offerId.next

      val actualResult = offerDao.get(offerId)
      val expectedResult = Option.empty[Offer]
      assertThat(actualResult, equalTo(expectedResult))
    }
  }

  describe("getAll") {

    it("existent") {
      val userId = Generators.userId.next
      val offer = Generators.offer.next
      offerDao.put(userId, offer)

      val actualResult = offerDao.getAll(userId)
      val expectedResult = Set(offer)
      assertThat(actualResult, equalTo(expectedResult))
    }

    it("nonexistent") {
      val userId = Generators.userId.next

      val actualResult = offerDao.getAll(userId)
      val expectedResult = Set.empty[Offer]
      assertThat(actualResult, equalTo(expectedResult))
    }
  }

  describe("put") {

    it("two offers") {
      val userId = Generators.userId.next
      val offer1 = Generators.offer.next
      val offer2 = Generators.offer.next
      offerDao.put(userId, offer1)
      offerDao.put(userId, offer2)

      val actualResult = offerDao.getAll(userId)
      val expectedResult = Set(offer1, offer2)
      assertThat(actualResult, equalTo(expectedResult))
    }

    it("update value") {
      val userId = Generators.userId.next
      val offer = Generators.offer.next
      val updatedOffer = offer.copy(title = offer.title + "1")
      offerDao.put(userId, offer)
      offerDao.put(userId, updatedOffer)

      val actualResult = offerDao.getAll(userId)
      val expectedResult = Set(updatedOffer)
      assertThat(actualResult, equalTo(expectedResult))
    }
  }

  describe("delete") {

    it("existent") {
      val userId = Generators.userId.next
      val offer = Generators.offer.next
      offerDao.put(userId, offer)

      val actualReturnedValue = offerDao.delete(offer.id)
      val expectedReturnedValue = true
      assertThat(actualReturnedValue, equalTo(expectedReturnedValue))

      val actualResult = offerDao.get(userId)
      val expectedResult = Option.empty[Offer]
      assertThat(actualResult, equalTo(expectedResult))
    }

    it("nonexistent") {
      val userId = Generators.userId.next
      val offer = Generators.offer.next

      val actualReturnedValue = offerDao.delete(offer.id)
      val expectedReturnedValue = false
      assertThat(actualReturnedValue, equalTo(expectedReturnedValue))

      val actualResult = offerDao.get(userId)
      val expectedResult = Option.empty[Offer]
      assertThat(actualResult, equalTo(expectedResult))
    }
  }
}
