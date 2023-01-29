package com.steffen.sales.coreapi.events.local

import org.axonframework.serialization.Revision
import java.util.*
@Revision("1")
data class OrderCreationRequestedEvent(
    val externalOrderNumber: String,

    val orderAmount: Double,

    val orderEntries: List<OrderCreationRequestedEventProduct>
)

data class OrderCreationRequestedEventProduct(
    val productId: String,
    val name: String,
    val price: Double
)
