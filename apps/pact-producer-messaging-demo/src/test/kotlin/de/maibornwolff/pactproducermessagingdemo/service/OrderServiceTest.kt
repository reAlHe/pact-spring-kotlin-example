package de.maibornwolff.pactproducermessagingdemo.service

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import de.maibornwolff.pactproducermessagingdemo.entity.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class OrderServiceTest {

    @Mock
    private lateinit var orderPublisherMock: OrderPublisher

    private lateinit var underTest: OrderService

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        underTest = OrderService(orderPublisherMock)
    }

    @Test
    fun `processOrder assigns id to order and processes order to publisher successfully`() {
        val order = Order("john.doe", setOf(1L, 2L))

        underTest.processOrder(order)

        argumentCaptor<Order>().apply {
            verify(orderPublisherMock, times(1)).publishMessage(capture())
            assertThat(firstValue.id).isNotNull()
        }
    }
}
