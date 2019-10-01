package de.maibornwolff.pactproducermessagingdemo.api.exposing.dto

import de.maibornwolff.pactproducermessagingdemo.entity.Order
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class OrderRequest(@NotBlank val recipient: String, @NotNull val products: Set<Long>) {

    fun toOrder(): Order {
        return Order(recipient, products)
    }
}
