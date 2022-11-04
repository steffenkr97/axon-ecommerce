package com.steffen.inventory.rest

import com.steffen.inventory.coreapi.commands.AddStockCommand
import com.steffen.inventory.coreapi.commands.CreateProductCommand
import com.steffen.inventory.coreapi.queries.FindStockByProductIdQuery
import com.steffen.inventory.coreapi.queries.FindStockByProductIdQueryResult
import com.steffen.inventory.rest.dto.ProductDto
import com.steffen.inventory.rest.dto.StockDto
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.axonframework.extensions.kotlin.query
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class InventoryController(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    @PostMapping("/product")
    fun addProduct(@RequestBody productDto: ProductDto): String {

        return commandGateway.sendAndWait(
            CreateProductCommand(
                productDto.productId,
                productDto.name,
                productDto.price
            )
        )
    }

    @PostMapping("/product/stock")
    fun addStock(@RequestBody stock: StockDto): String {

        commandGateway.send<String>(
            AddStockCommand(
                stock.productId,
                stock.diff
            )
        )
        return "Send"
    }


    @GetMapping("/product/{productId}")
    fun getStockForProduct(@PathVariable productId: String): FindStockByProductIdQueryResult? {
        return queryGateway.query<FindStockByProductIdQueryResult, FindStockByProductIdQuery>(
            FindStockByProductIdQuery(
                productId
            )
        ).get()
    }
}