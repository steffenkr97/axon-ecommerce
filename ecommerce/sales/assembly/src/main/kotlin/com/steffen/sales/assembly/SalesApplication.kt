package com.steffen.sales.assembly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.steffen.sales")
class SalesApplication

fun main(args: Array<String>) {
	runApplication<SalesApplication>(*args)
}
