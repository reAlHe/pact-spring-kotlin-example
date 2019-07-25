package de.maibornwolff.pactconsumerdemo.service

import de.maibornwolff.pactconsumerdemo.entity.Product
import java.util.*

interface ProductDetailsAdapter {

    fun fetchProductDetails(productId: Long): Product?
}
