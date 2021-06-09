package com.semkagtn.backend.http

import akka.http.scaladsl.server.Route
import com.semkagtn.backend.service.OfferUserService

class OfferUserController(offerUserService: OfferUserService)
  extends ControllerBase {

  override val route: Route =
    getUserRoute ~
      getOfferRoute

  private def getUserRoute: Route = (get & path("user" / LongNumber)) { userId =>
    String.valueOf {
      offerUserService.getUser(userId)
    } // marshalling
  }

  private def getOfferRoute: Route = (get & path("offer" / LongNumber)) { offerId =>
    String.valueOf {
      offerUserService.getOffer(offerId)
    } // marshalling
  }
}
