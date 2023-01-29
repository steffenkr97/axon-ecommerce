package com.steffen.sales.coreapi.commands

import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.serialization.Revision

@Revision("1")
data class CreateOrderCommand(
    @AggregateIdentifier
    val orderNumber: String,

    val orderAmount: Double,

    val orderEntries: List<CreateOrderCommand>
)

data class CreateOrderCommandProduct(
    val productId: String,
    val name: String,
    val price: Double
)
