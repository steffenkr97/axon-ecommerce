package com.steffen.inventory.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class InventoryController {

    @GetMapping("/hello")
    fun test():String {
        return "hello"
    }

    @PostMapping("/story")
    fun story(@RequestBody story: String) = "Hallo deine Story ist folgendes: $story"
}