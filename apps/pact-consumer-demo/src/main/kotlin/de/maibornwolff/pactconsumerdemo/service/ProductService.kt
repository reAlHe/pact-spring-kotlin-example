package de.maibornwolff.pactconsumerdemo.service

import de.maibornwolff.pactconsumerdemo.entity.Product
import de.maibornwolff.pactconsumerdemo.service.error.ProductNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(@Autowired private val productDetailsAdapter: ProductDetailsAdapter) {

    fun fetchProductDetailsForId(productId: Long): Product {
       return productDetailsAdapter.fetchProductDetails(productId) ?: throw ProductNotFoundException("No product with id: $productId")
    }

}
