package com.semkagtn.backend.service

import com.semkagtn.backend.offer.InMemoryOfferDao
import com.semkagtn.backend.user.InMemoryUserDao
import com.semkagtn.backend.util.IdGenerator

class OfferUserServiceImplSpec
  extends OfferUserServiceSpecBase {

  override val offerUserService: OfferUserService =
    new OfferUserServiceImpl(
      userDao = new InMemoryUserDao,
      offerDao = new InMemoryOfferDao,
      idGenerator = new IdGenerator
    )
}
