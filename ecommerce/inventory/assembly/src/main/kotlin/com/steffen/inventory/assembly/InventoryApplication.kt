package com.steffen.inventory.assembly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
//@ComponentScan("com.steffen.inventory")
class InventoryApplication

fun main(args: Array<String>) {
	runApplication<InventoryApplication>(*args)
}
