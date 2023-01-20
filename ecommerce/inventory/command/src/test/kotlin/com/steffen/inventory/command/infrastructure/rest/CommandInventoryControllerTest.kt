package com.steffen.inventory.command.infrastructure.rest

import com.steffen.inventory.command.TestFixtures
import com.steffen.inventory.coreapi.commands.CreateProductCommand
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.commandhandling.gateway.CommandGateway
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CommandInventoryControllerTest {

    private val commandGateway = mockk<CommandGateway>()
    private val inventoryController = CommandInventoryController(commandGateway)



    @Test
    fun `send command for adding a product`() {

        // GIVEN
        justRun { commandGateway.sendAndWait(any<CreateProductCommand>()) }

        // WHEN
        inventoryController.addProduct(TestFixtures.productDto())

        // THEN
        val command = slot<CreateProductCommand>()
        verify { commandGateway.sendAndWait(capture(command)) }

        assertThat(command.isCaptured)
    }

    @Test
    fun manipulateStock() {
    }
}