package com.steffen.inventory.query.application

import com.steffen.inventory.coreapi.events.ProductCreatedEvent
import com.steffen.inventory.coreapi.events.StockChangedEvent
import com.steffen.inventory.coreapi.queries.FindStockByProductIdQuery
import com.steffen.inventory.coreapi.queries.FindStockByProductIdQueryResult
import com.steffen.inventory.query.domain.service.StockProjection
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup(ProductHandler.NAME)
class ProductHandler(
    private val stockProjection: StockProjection
) {

    companion object {
        const val NAME = "ProductHandler"
    }

    @EventHandler
    fun on(evt: ProductCreatedEvent) {
        stockProjection.addItem(evt.productId)
    }

    @EventHandler
    fun on(evt: StockChangedEvent) {
        stockProjection.changeStock(evt.productId, evt.diff)
    }

    @QueryHandler
    fun answer(query: FindStockByProductIdQuery): FindStockByProductIdQueryResult {
        val result = stockProjection.findStockById(query.productId)
        return FindStockByProductIdQueryResult(query.productId, result)
    }
}