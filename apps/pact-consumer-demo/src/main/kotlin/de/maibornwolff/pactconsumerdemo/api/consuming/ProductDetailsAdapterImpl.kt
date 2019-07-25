package de.maibornwolff.pactconsumerdemo.api.consuming

import de.maibornwolff.pactconsumerdemo.entity.Product
import de.maibornwolff.pactconsumerdemo.service.ProductDetailsAdapter
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductDetailsAdapterImpl(@Autowired private val productDetailsClient: ProductDetailsClient) : ProductDetailsAdapter {

    private val logger: Logger = LoggerFactory.getLogger(ProductDetailsAdapterImpl::class.java)

    override fun fetchProductDetails(productId: Long): Product? {
        return try {
            productDetailsClient.fetchProductDetail(productId).body
        }
        catch (e: FeignException) {
            logger.error("An error occured during fetch of product details: ${e.localizedMessage}")
            null
        }
    }
}
