package de.maibornwolff.pactproviderdemo.api.exposing

import de.maibornwolff.pactproviderdemo.entity.ProductDetails
import de.maibornwolff.pactproviderdemo.entity.ProductDetailsRepository
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class ProductDetailsControllerTest {

    @Autowired
    private lateinit var underTest: MockMvc

    @Autowired
    private lateinit var productDetailsRepository: ProductDetailsRepository

    @Test
    fun `fetch product details successfully`() {
        val productId = 1L
        var productDetails = ProductDetails(productId, "Awesome product no 1", 4.99)
        productDetailsRepository.save(productDetails)

        underTest.get("/productDetails/{id}", productId)
                .andExpect {
                    status { `is`(200) }
                    jsonPath("$.id", CoreMatchers.equalTo(1))
                    jsonPath("$.name", CoreMatchers.equalTo("Awesome product no 1"))
                    jsonPath("$.price", CoreMatchers.equalTo(4.99))
                }
    }
}
