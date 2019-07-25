package de.maibornwolff.pactproviderdemo.entity

import org.springframework.data.repository.CrudRepository

interface ProductDetailsRepository : CrudRepository<ProductDetails, Long> {

    fun findProductDetailsById(productId: Long): ProductDetails?

}
