package de.maibornwolff.pactproviderdemo.service

import de.maibornwolff.pactproviderdemo.entity.ProductDetails
import de.maibornwolff.pactproviderdemo.entity.ProductDetailsRepository
import de.maibornwolff.pactproviderdemo.service.error.ProductDetailsNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class ProductDetailsServiceTest {

    @Mock
    private lateinit var productDetailsRepositoryMock: ProductDetailsRepository

    private lateinit var underTest: ProductDetailsService

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        underTest = ProductDetailsService(productDetailsRepositoryMock)
    }

    @Test
    fun `fetch product details successfully`() {
        val id = 1L
        val productDetails = ProductDetails(id, "My awesome product no 1", 4.99)

        Mockito.`when`(productDetailsRepositoryMock.findProductDetailsById(id)).thenReturn(productDetails)

        val result = underTest.getProductDetailsForId(id)

        assertThat(result).isEqualTo(productDetails)
    }

    @Test
    fun `fetch product details throws exception if no product details available`() {
        val id = 1L

        Mockito.`when`(productDetailsRepositoryMock.findProductDetailsById(id)).thenReturn(null)

        assertThatExceptionOfType(ProductDetailsNotFoundException::class.java).isThrownBy { underTest.getProductDetailsForId(id) }
    }
}
