package com.steffen.inventory.rest.dto

data class StockDto(
    val productId: String,
    val diff: Int
)