package de.maibornwolff.pactconsumerdemo.service

import de.maibornwolff.pactconsumerdemo.entity.Product
import de.maibornwolff.pactconsumerdemo.service.error.ProductNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

internal class ProductServiceTest {

    @Mock
    private lateinit var productDetailsAdapterImpl: ProductDetailsAdapter

    private lateinit var underTest: ProductService

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        underTest = ProductService(productDetailsAdapterImpl)
    }

    @Test
    fun `fetch product details successfully`() {
        val productId = 1L
        val productDetails = Product(productId, "Awesome Product No 1", 4.99)

        Mockito.`when`(productDetailsAdapterImpl.fetchProductDetails(productId)).thenReturn(productDetails)

        val result = underTest.fetchProductDetailsForId(productId)

        assertThat(result).isEqualTo(productDetails)
    }

    @Test
    fun `fetch product details throws exception if product not found`() {
        val productId = 1L

        Mockito.`when`(productDetailsAdapterImpl.fetchProductDetails(productId)).thenReturn(null)

        assertThatExceptionOfType(ProductNotFoundException::class.java).isThrownBy { underTest.fetchProductDetailsForId(productId) }
    }
}
