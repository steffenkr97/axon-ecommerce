package com.steffen.inventory.query.infrastructure.rest

import com.steffen.inventory.coreapi.queries.FindStockByProductIdQuery
import com.steffen.inventory.coreapi.queries.FindStockByProductIdQueryResult
import org.axonframework.extensions.kotlin.query
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/query")
class QueryInventoryController(
    private val queryGateway: QueryGateway
) {

    @GetMapping("/product/{productId}")
    fun getStockForProduct(@PathVariable productId: String): FindStockByProductIdQueryResult? {
        return queryGateway.query<FindStockByProductIdQueryResult, FindStockByProductIdQuery>(
            FindStockByProductIdQuery(
                productId
            )
        ).get()
    }
}