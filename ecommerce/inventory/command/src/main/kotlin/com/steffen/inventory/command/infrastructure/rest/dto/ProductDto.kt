package com.steffen.inventory.command.infrastructure.rest.dto

data class ProductDto(
    val productId: String,
    val name: String,
    val price: Double
    )