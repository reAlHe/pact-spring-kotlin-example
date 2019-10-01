package de.maibornwolff.pactconsumerdemo.entity

import org.springframework.data.repository.CrudRepository

interface OrderRepository: CrudRepository<Order, Long> {
}
