package de.maibornwolff.pactproviderdemo.api.exposing

import de.maibornwolff.pactproviderdemo.api.exposing.dto.ProductDetailsResponse
import de.maibornwolff.pactproviderdemo.service.ProductDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/productDetails")
class ProductDetailsController(@Autowired private val productDetailsService: ProductDetailsService) {

    @GetMapping("/{productId}")
    fun getProductDetails(@PathVariable productId: Long): ResponseEntity<ProductDetailsResponse> {
        val productDetails = productDetailsService.getProductDetailsForId(productId)
        return ResponseEntity.ok(ProductDetailsResponse.fromProductDetails(productDetails))
    }

}
