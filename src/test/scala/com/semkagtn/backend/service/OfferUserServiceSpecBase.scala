package com.semkagtn.backend.service

import com.semkagtn.backend.{Generators, SpecBase}
import com.semkagtn.backend.offer.Offer
import com.semkagtn.backend.user.{User, UserWithOffers}

trait OfferUserServiceSpecBase
  extends SpecBase {

  def offerUserService: OfferUserService

  private val fakeId = -1L

  describe("createUser") {

    it("create") {
      val request = Generators.userCreateRequest.next
      val createdUser = offerUserService.createUser(request)

      // fakeId: There are better solutions
      val actualResult = createdUser.copy(
        user = createdUser.user.copy(id = fakeId)
      )
      val expectedResult = UserWithOffers(
        user = User(id = fakeId, name = request.name),
        offers = Set.empty
      )
      assertThat(actualResult, equalTo(expectedResult))
    }
  }

  describe("getUser") {

    it("existent") {
      val userCreateRequest = Generators.userCreateRequest.next
      val user = offerUserService.createUser(userCreateRequest)
      val userId = user.user.id

      val offerCreateRequest = Generators.offerCreateRequest.next.copy(userId = userId)
      val offer = offerUserService.createOffer(offerCreateRequest)

      val actualResult = offerUserService.getUser(userId)
      val expectedResult = Some(user.copy(offers = Set(offer)))
      assertThat(actualResult, equalTo(expectedResult))
    }

    it("nonexistent") {
      val actualResult = offerUserService.getUser(-1)
      val expectedResult = Option.empty[UserWithOffers]
      assertThat(actualResult, equalTo(expectedResult))
    }
  }

  describe("getOffer") {

    it("existent") {
      val userCreateRequest = Generators.userCreateRequest.next
      val user = offerUserService.createUser(userCreateRequest)
      val userId = user.user.id

      val offerCreateRequest = Generators.offerCreateRequest.next.copy(userId = userId)
      val offer = offerUserService.createOffer(offerCreateRequest)

      val actualResult = offerUserService.getOffer(offer.id)
      val expectedResult = Some(offer)
      assertThat(actualResult, equalTo(expectedResult))
    }

    it("nonexistent") {
      val actualResult = offerUserService.getOffer(-1)
      val expectedResult = Option.empty[Offer]
      assertThat(actualResult, equalTo(expectedResult))
    }
  }

  describe("deleteUser") {

    it("also deletes offer") {
      val userCreateRequest = Generators.userCreateRequest.next
      val user = offerUserService.createUser(userCreateRequest)
      val userId = user.user.id

      val offerCreateRequest = Generators.offerCreateRequest.next.copy(userId = userId)
      val offer = offerUserService.createOffer(offerCreateRequest)

      offerUserService.deleteUser(userId)

      val actualUser = offerUserService.getUser(userId)
      val expectedUser = Option.empty[User]
      assertThat(actualUser, equalTo(expectedUser))

      val actualOffer = offerUserService.getOffer(offer.id)
      val expectedOffer = Option.empty[Offer]
      assertThat(actualOffer, equalTo(expectedOffer))
    }
  }
}
