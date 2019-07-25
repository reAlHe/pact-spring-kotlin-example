package de.maibornwolff.pactconsumerdemo.api.consuming

import de.maibornwolff.pactconsumerdemo.entity.Product
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Component
@FeignClient(name = "server", url = "\${provider.url}", decode404 = false)
interface ProductDetailsClient {

    @GetMapping("/productDetails/{productId}")
    fun fetchProductDetail(@PathVariable("productId") productId: Long): ResponseEntity<Product>

}
