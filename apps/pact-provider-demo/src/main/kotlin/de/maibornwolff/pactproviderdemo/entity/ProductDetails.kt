package de.maibornwolff.pactproviderdemo.entity

import org.springframework.data.annotation.Id

data class ProductDetails(@Id val id: Long, val name: String, val price: Double)
