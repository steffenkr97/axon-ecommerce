package com.steffen.inventory.query.domain.service

import org.springframework.stereotype.Component

@Component
class StockProjection {

    private val productStock = HashMap<String, Int>()

    fun addItem(productId: String) {
        productStock[productId] = 0
    }

    fun changeStock(productId: String, diff: Int) {
        productStock[productId] = productStock[productId]?.plus(diff)!!
    }

    fun findStockById(productId: String) = productStock[productId]!!

}