package de.maibornwolff.pactconsumerdemo.api.exposing

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.model.RequestResponsePact
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(PactConsumerTestExt::class)
@PactTestFor("Service-B-provider", "localhost", "7000")
internal class ProductControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Pact(consumer="Service-A-consumer")
    fun createPact(builder: PactDslWithProvider): RequestResponsePact {
        val json = PactDslJsonBody().numberValue("id", 1).stringValue("name", "Awesome product no 1").numberValue("price", 4.99).asBody()

        return builder.given("provider has product details for requested product")
                .uponReceiving("a request to fetch the product details")
                .path("/productDetails/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(json)
                .toPact()
    }

    @Test
    @PactTestFor
    fun `fetch product details successfully`() {
        mvc.get("/products/{productId}", 1L).andExpect {
            status { `is`(200) }
            jsonPath("$.id", equalTo(1))
            jsonPath("$.name", equalTo("Awesome product no 1"))
            jsonPath("$.price", equalTo(4.99))
        }
    }
}

