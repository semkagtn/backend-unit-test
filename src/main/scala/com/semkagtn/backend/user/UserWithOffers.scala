package com.semkagtn.backend.user

import com.semkagtn.backend.offer.Offer

final case class UserWithOffers(user: User,
                                offers: Set[Offer])
