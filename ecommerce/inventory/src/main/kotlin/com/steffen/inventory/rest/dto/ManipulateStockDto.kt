package com.steffen.inventory.rest.dto

data class ManipulateStockDto(
    val productId: String,
    val diff: Int
)