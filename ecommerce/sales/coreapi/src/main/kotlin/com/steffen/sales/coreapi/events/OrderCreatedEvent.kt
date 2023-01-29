package com.steffen.sales.coreapi.events

import org.axonframework.serialization.Revision
import java.util.*

@Revision("1")
data class OrderCreatedEvent(
    val externalOrderNumber: String,
    val internalOrderNumber: UUID,

    val orderAmount: Double,
    val orderEntries: List<OrderCreatedEventProduct>
)

data class OrderCreatedEventProduct(
    val productId: String,
    val name: String,
    val price: Double
)
