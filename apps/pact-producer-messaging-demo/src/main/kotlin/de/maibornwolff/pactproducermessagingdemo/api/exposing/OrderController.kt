package de.maibornwolff.pactproducermessagingdemo.api.exposing

import de.maibornwolff.pactproducermessagingdemo.api.exposing.dto.OrderRequest
import de.maibornwolff.pactproducermessagingdemo.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(@Autowired private val orderService: OrderService) {

    @PostMapping
    fun placeOrder(@RequestBody orderRequest: OrderRequest): ResponseEntity<Void> {
        orderService.processOrder(orderRequest.toOrder())
        return ResponseEntity(HttpStatus.CREATED)
    }

}
