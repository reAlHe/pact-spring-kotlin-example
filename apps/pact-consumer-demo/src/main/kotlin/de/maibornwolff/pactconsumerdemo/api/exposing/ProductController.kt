package de.maibornwolff.pactconsumerdemo.api.exposing

import de.maibornwolff.pactconsumerdemo.api.exposing.dto.ProductResponse
import de.maibornwolff.pactconsumerdemo.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController(@Autowired private val productService: ProductService) {

    @GetMapping("/{productId}")
    fun getProduct(@PathVariable productId: Long): ResponseEntity<ProductResponse> {
        val product = productService.fetchProductDetailsForId(productId)
        return ResponseEntity.ok(ProductResponse.fromProduct(product))
    }

}
