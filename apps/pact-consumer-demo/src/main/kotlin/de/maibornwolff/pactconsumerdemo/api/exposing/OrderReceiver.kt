package de.maibornwolff.pactconsumerdemo.api.exposing

import de.maibornwolff.pactconsumerdemo.entity.Order
import de.maibornwolff.pactconsumerdemo.service.OrderService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderReceiver(@Autowired private val orderService: OrderService) {

    @RabbitListener(queues = ["orders"], containerFactory = "simpleRabbitListenerContainerFactory")
    fun receive(order: Order) {
        orderService.storeOrder(order)
    }

}
