package com.steffen.inventory.command.domain.model


import com.steffen.inventory.coreapi.commands.AddStockCommand
import com.steffen.inventory.coreapi.commands.CreateProductCommand
import com.steffen.inventory.coreapi.commands.ReduceStockCommand
import com.steffen.inventory.coreapi.events.ProductCreatedEvent
import com.steffen.inventory.coreapi.events.StockChangedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ProductAggregate {


    companion object {
        @CommandHandler
        @JvmStatic
        fun handle(
            cmd: CreateProductCommand
        ): ProductAggregate {
            return ProductAggregate().apply {
                AggregateLifecycle.apply(
                    ProductCreatedEvent(
                        cmd.productId,
                        cmd.name,
                        cmd.price
                    )
                )
            }
        }

    }

    @AggregateIdentifier
    var productId: String? = null
    var stock: Int = 0

    @CommandHandler
    fun handle(cmd: AddStockCommand) {
        require(cmd.amount > 0) { "Amount of stock must be > 0" }
        AggregateLifecycle.apply(StockChangedEvent(cmd.productId, cmd.amount))
    }

    @CommandHandler
    fun handle(cmd: ReduceStockCommand) {
        require(cmd.amount < 0) { "Amount of stock must be < 0"}
        AggregateLifecycle.apply(StockChangedEvent(cmd.productId, cmd.amount))
    }

    @EventSourcingHandler
    fun on(evt: StockChangedEvent) {
        stock += evt.diff
    }

    @EventSourcingHandler
    fun on(evt: ProductCreatedEvent) {
        productId = evt.productId
        stock = 0
    }
}