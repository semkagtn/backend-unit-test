package com.semkagtn.backend.service

import com.semkagtn.backend.offer.Payload
import com.semkagtn.backend.user.UserId

final case class OfferCreateRequest(userId: UserId,
                                    title: String,
                                    price: BigDecimal,
                                    payload: Payload)
