package com.steffen.inventory.command.infrastructure.rest.dto

data class ManipulateStockDto(
    val productId: String,
    val diff: Int
)