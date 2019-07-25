package de.maibornwolff.pactconsumerdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class PactConsumerDemoApplication

fun main(args: Array<String>) {
	runApplication<PactConsumerDemoApplication>(*args)
}
