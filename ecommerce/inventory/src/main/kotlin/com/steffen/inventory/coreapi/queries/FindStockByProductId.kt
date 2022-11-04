package com.steffen.inventory.coreapi.queries

data class FindStockByProductIdQuery(val productId: String)
data class FindStockByProductIdQueryResult(val productId: String, val stock: Int)