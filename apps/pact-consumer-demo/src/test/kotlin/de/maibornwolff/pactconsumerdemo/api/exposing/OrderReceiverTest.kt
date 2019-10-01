package de.maibornwolff.pactconsumerdemo.api.exposing

import au.com.dius.pact.consumer.MessagePactBuilder
import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.consumer.junit5.ProviderType
import au.com.dius.pact.model.v3.messaging.Message
import au.com.dius.pact.model.v3.messaging.MessagePact
import com.fasterxml.jackson.databind.ObjectMapper
import de.maibornwolff.pactconsumerdemo.entity.Order
import de.maibornwolff.pactconsumerdemo.entity.OrderRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "Service-C-messaging-provider", providerType = ProviderType.ASYNCH)
internal class OrderReceiverTest {

    @Autowired private lateinit var orderRepository: OrderRepository

    @Autowired private lateinit var orderReceiver: OrderReceiver

    @Autowired private lateinit var objectMapper: ObjectMapper

    private val UUID_REGEX = "([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})"

    @Pact(consumer="Service-A-consumer")
    fun createPact(builder: MessagePactBuilder): MessagePact {
        val body = PactDslJsonBody().stringMatcher("id", UUID_REGEX, UUID.randomUUID().toString()).stringValue("recipient", "John Doe").array("products").numberValue(1L).numberValue(2L).closeArray().asBody()

        return builder.expectsToReceive("an order message").withContent(body).toPact()
    }

    @Test
    @PactTestFor(pactMethod = "createPact")
    fun `fetch product details successfully`(messages: List<Message>) {
        val incomingOrder = objectMapper.readValue(messages[0].contentsAsBytes(), Order::class.java)

        orderReceiver.receive(incomingOrder)
        val result = orderRepository.findAll()

        assertThat(result).hasSize(1)
        val storedOrder = result.toList()[0]

        assertThat(storedOrder).isEqualTo(incomingOrder)
    }
}

