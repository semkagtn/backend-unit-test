package com.semkagtn.backend.offer

final case class Offer(id: OfferId,
                       title: String,
                       price: BigDecimal,
                       payload: Payload)
