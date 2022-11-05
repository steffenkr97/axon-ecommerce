package com.steffen.inventory.coreapi.events

data class StockChangedEvent(
    val productId: String,
    val diff: Int
)