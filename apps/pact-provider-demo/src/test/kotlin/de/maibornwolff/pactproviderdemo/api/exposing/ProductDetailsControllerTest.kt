package de.maibornwolff.pactproviderdemo.api.exposing

import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.State
import au.com.dius.pact.provider.junit.loader.PactBroker
import au.com.dius.pact.provider.spring.SpringRestPactRunner
import de.maibornwolff.pactproviderdemo.entity.ProductDetails
import de.maibornwolff.pactproviderdemo.entity.ProductDetailsRepository
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import au.com.dius.pact.provider.junit.target.HttpTarget
import au.com.dius.pact.provider.junit.target.TestTarget
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRestPactRunner::class)
@Provider("Service-B-provider")
@PactBroker(host= "localhost")
internal class ProductDetailsControllerTest {

    @TestTarget
    val target: SpringBootHttpTarget = SpringBootHttpTarget()

    @Autowired
    private lateinit var productDetailsRepository: ProductDetailsRepository

    @State("provider has product details for requested product")
    fun insertProductDetails() {
        var productDetails = ProductDetails(1L, "Awesome product no 1", 4.99)
        productDetailsRepository.save(productDetails)
    }
}