package com.steffen.inventory.coreapi.events

data class ProductCreatedEvent(
    val productId: String,
    val name: String,
    val price: Double
)