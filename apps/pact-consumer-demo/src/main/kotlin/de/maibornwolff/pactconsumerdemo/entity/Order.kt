package de.maibornwolff.pactconsumerdemo.entity

import java.util.*

data class Order(val id: UUID, val recipient: String, val products: Set<Long>)
