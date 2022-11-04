package com.steffen.inventory.rest

import com.steffen.inventory.coreapi.commands.AddStockCommand
import com.steffen.inventory.coreapi.commands.CreateProductCommand
import com.steffen.inventory.coreapi.commands.ReduceStockCommand
import com.steffen.inventory.coreapi.queries.FindStockByProductIdQuery
import com.steffen.inventory.coreapi.queries.FindStockByProductIdQueryResult
import com.steffen.inventory.rest.dto.ProductDto
import com.steffen.inventory.rest.dto.ManipulateStockDto
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.axonframework.extensions.kotlin.query
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun manipulateStock(@RequestBody stock: ManipulateStockDto): ResponseEntity<String> {

        return when {
            stock.diff == 0 -> ResponseEntity<String>("Diff cannot be 0", HttpStatus.BAD_REQUEST)
            stock.diff < 0 -> ResponseEntity(
                commandGateway.sendAndWait<String>(
                    ReduceStockCommand(
                        stock.productId,
                        stock.diff
                    )
                ), HttpStatus.ACCEPTED
            )
            else -> ResponseEntity(
                commandGateway.sendAndWait<String>(AddStockCommand(stock.productId, stock.diff)),
                HttpStatus.ACCEPTED
            )
        }
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