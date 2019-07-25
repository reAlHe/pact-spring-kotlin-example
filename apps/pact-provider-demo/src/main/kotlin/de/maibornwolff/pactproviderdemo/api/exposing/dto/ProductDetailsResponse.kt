package de.maibornwolff.pactproviderdemo.api.exposing.dto

import de.maibornwolff.pactproviderdemo.entity.ProductDetails

data class ProductDetailsResponse(val id: Long, val name: String, val price: Double) {

    companion object {
        fun fromProductDetails(productDetails: ProductDetails) : ProductDetailsResponse {
            return ProductDetailsResponse(productDetails.id, productDetails.name, productDetails.price)
        }
    }

}
