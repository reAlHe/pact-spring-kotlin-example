package de.maibornwolff.pactproducermessagingdemo.entity

import java.util.*

data class Order(val recipient: String, val products: Set<Long>) {
    var id: UUID? = null
}
