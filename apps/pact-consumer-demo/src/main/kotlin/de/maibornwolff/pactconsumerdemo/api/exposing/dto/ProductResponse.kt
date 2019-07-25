package de.maibornwolff.pactconsumerdemo.api.exposing.dto

import de.maibornwolff.pactconsumerdemo.entity.Product
import java.util.*

data class ProductResponse(val id: Long, val name: String, val price: Double) {

    companion object {
        fun fromProduct(product: Product): ProductResponse {
            return ProductResponse(product.id, product.name, product.price)
        }
    }

}
