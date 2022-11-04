package com.steffen.inventory.projection

import com.steffen.inventory.coreapi.events.ProductCreatedEvent
import com.steffen.inventory.coreapi.events.StockChangedEvent
import com.steffen.inventory.coreapi.queries.FindStockByProductIdQuery
import com.steffen.inventory.coreapi.queries.FindStockByProductIdQueryResult
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup(ProductHandler.NAME)
class ProductHandler {

    companion object {
        const val NAME = "ProductEventHandler"
    }

    val productStock = HashMap<String, Int>()


    @EventHandler
    fun on(evt: ProductCreatedEvent) {
        productStock[evt.productId] = 0
    }

    @EventHandler
    fun on(evt: StockChangedEvent) {
        productStock[evt.productId] = productStock[evt.productId]?.plus(evt.diff)!!
    }

    @QueryHandler
    fun answer(query: FindStockByProductIdQuery): FindStockByProductIdQueryResult {
        val result = productStock[query.productId]!!
        return FindStockByProductIdQueryResult(query.productId, result)
    }
}