package de.maibornwolff.pactproviderdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PactProviderDemoApplication

fun main(args: Array<String>) {
	runApplication<PactProviderDemoApplication>(*args)
}
