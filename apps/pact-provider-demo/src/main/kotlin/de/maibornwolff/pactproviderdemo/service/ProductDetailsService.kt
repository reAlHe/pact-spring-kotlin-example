package de.maibornwolff.pactproviderdemo.service

import de.maibornwolff.pactproviderdemo.entity.ProductDetails
import de.maibornwolff.pactproviderdemo.entity.ProductDetailsRepository
import de.maibornwolff.pactproviderdemo.service.error.ProductDetailsNotFoundException
import org.springframework.stereotype.Service

@Service
class ProductDetailsService(private val productDetailsRepository: ProductDetailsRepository) {

    fun getProductDetailsForId(productId: Long) : ProductDetails {
        return productDetailsRepository.findProductDetailsById(productId) ?: throw ProductDetailsNotFoundException("No product details found for product with id: $productId")
    }
}
