package com.steffen.inventory.command

import com.steffen.inventory.command.infrastructure.rest.dto.ProductDto
import com.steffen.inventory.coreapi.commands.CreateProductCommand

object TestFixtures {

    const val PRODUCT_ID = "00001"
    const val PRODUCT_NAME = "Tasse"
    const val PRODUCT_PRICE = 5.99

    fun productDto() = ProductDto(
        productId = PRODUCT_ID,
        name = PRODUCT_NAME,
        price = PRODUCT_PRICE
    )


    fun createProductCommand() = CreateProductCommand(
        productId = PRODUCT_ID,
        name =  PRODUCT_NAME,
        price = PRODUCT_PRICE
    )
}