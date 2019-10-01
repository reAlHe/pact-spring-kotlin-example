package de.maibornwolff.pactproducermessagingdemo.api.consuming

import de.maibornwolff.pactproducermessagingdemo.EXCHANGE
import de.maibornwolff.pactproducermessagingdemo.ROUTING_KEY
import de.maibornwolff.pactproducermessagingdemo.entity.Order
import de.maibornwolff.pactproducermessagingdemo.service.OrderPublisher
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderPublisherImpl(@Autowired private val rabbitTemplate: RabbitTemplate) : OrderPublisher {
    override fun publishMessage(order: Order) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, order)
    }
}
