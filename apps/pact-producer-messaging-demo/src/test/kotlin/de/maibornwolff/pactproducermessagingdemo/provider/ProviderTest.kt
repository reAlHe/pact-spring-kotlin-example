package de.maibornwolff.pactproducermessagingdemo.provider

import au.com.dius.pact.provider.PactVerifyProvider
import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.loader.PactBroker
import au.com.dius.pact.provider.junit.target.AmqpTarget
import au.com.dius.pact.provider.junit.target.Target
import au.com.dius.pact.provider.junit.target.TestTarget
import au.com.dius.pact.provider.spring.SpringMessagePactRunner
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import de.maibornwolff.pactproducermessagingdemo.api.exposing.dto.OrderRequest
import de.maibornwolff.pactproducermessagingdemo.entity.Order
import de.maibornwolff.pactproducermessagingdemo.service.OrderPublisher
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringMessagePactRunner::class)
@Provider("Service-C-messaging-provider")
@PactBroker(host = "localhost")
class ProviderTest {

    @TestTarget
    var target: Target = AmqpTarget()

    @SpyBean
    private lateinit var orderPublisher: OrderPublisher

    @Autowired
    private lateinit var underTest: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @PactVerifyProvider("an order message")
    fun `place order triggers message successfully`(): String {
        val order = OrderRequest("John Doe", setOf(1L, 2L))

        underTest.post("/orders") {
            content = mapper.writeValueAsString(order)
            contentType = MediaType.APPLICATION_JSON
        }
                .andExpect {
                    status { `is`(201) }
                }

        val message: String
        argumentCaptor<Order>().apply {
            verify(orderPublisher, times(1)).publishMessage(capture())
            message = mapper.writeValueAsString(firstValue)
        }

        return message
    }
}
