package de.maibornwolff.pactconsumerdemo.service

import de.maibornwolff.pactconsumerdemo.entity.Order
import de.maibornwolff.pactconsumerdemo.entity.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired private val orderRepository: OrderRepository) {

    fun storeOrder(order: Order) {
        orderRepository.save(order)
    }

}
