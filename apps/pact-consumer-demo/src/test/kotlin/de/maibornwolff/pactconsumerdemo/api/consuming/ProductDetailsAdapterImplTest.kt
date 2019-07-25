package de.maibornwolff.pactconsumerdemo.api.consuming

import de.maibornwolff.pactconsumerdemo.entity.Product
import de.maibornwolff.pactconsumerdemo.service.error.ProductNotFoundException
import feign.FeignException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

internal class ProductDetailsAdapterImplTest {

    @Mock
    private lateinit var productDetailsClient: ProductDetailsClient

    private lateinit var underTest: ProductDetailsAdapterImpl

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        underTest = ProductDetailsAdapterImpl(productDetailsClient)
    }

    @Test
    fun `fetch product details successfully`() {
        val productId = 1L
        val productDetails = Product(productId, "Awesome Product No 1", 4.99)

        Mockito.`when`(productDetailsClient.fetchProductDetail(productId)).thenReturn(ResponseEntity(productDetails, HttpStatus.OK))

        val result = underTest.fetchProductDetails(productId)

        assertThat(result).isEqualTo(productDetails)
    }


    @Test
    fun `fetch product details not successfully with 404`() {
        val productId = 1L

        Mockito.`when`(productDetailsClient.fetchProductDetail(productId)).then {throw FeignException.NotFound("Not found", "Not found".toByteArray()) }

        val result = underTest.fetchProductDetails(productId)

        assertThat(result).isNull()
    }
}
