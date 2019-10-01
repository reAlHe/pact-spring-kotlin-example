package de.maibornwolff.pactproducermessagingdemo.service

import de.maibornwolff.pactproducermessagingdemo.entity.Order

interface OrderPublisher {

    fun publishMessage(order: Order)
}
