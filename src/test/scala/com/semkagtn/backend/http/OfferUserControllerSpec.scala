package com.semkagtn.backend.http

import akka.http.scaladsl.model.StatusCodes
import com.semkagtn.backend.Generators
import com.semkagtn.backend.service.OfferUserService

class OfferUserControllerSpec
  extends ControllerSpecBase {

  private val offerUserService = mock[OfferUserService]

  override val controller: ControllerBase =
    new OfferUserController(offerUserService)

  describe("GET /user/{userId}") {

    it("return 200") {
      val userId = Generators.userId.next
      val user = Generators.userWithOffers.next
      (offerUserService.getUser _).expects(userId).returns(Some(user))

      Get(s"/user/$userId") ~> controller.route ~> check {
        status shouldBe StatusCodes.OK
        // check parsed equal to 'user'
      }
    }

    it("return 500") {
      val userId = Generators.userId.next
      (offerUserService.getUser _).expects(userId).throws(new IllegalStateException)

      Get(s"/user/$userId") ~> controller.route ~> check {
        status shouldBe StatusCodes.InternalServerError
      }
    }
  }

  // ...
}
