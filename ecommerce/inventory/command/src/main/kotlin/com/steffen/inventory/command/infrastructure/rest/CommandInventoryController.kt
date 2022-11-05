package com.steffen.inventory.command.infrastructure.rest

import com.steffen.inventory.command.infrastructure.rest.dto.ManipulateStockDto
import com.steffen.inventory.command.infrastructure.rest.dto.ProductDto
import com.steffen.inventory.coreapi.commands.AddStockCommand
import com.steffen.inventory.coreapi.commands.CreateProductCommand
import com.steffen.inventory.coreapi.commands.ReduceStockCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/command")
class CommandInventoryController(
    private val commandGateway: CommandGateway
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


}