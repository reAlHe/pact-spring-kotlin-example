package de.maibornwolff.pactproducermessagingdemo.service

import de.maibornwolff.pactproducermessagingdemo.entity.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(@Autowired private val orderPublisher: OrderPublisher) {

    fun processOrder(order: Order) {
        order.id = UUID.randomUUID()
        orderPublisher.publishMessage(order)
    }

}
