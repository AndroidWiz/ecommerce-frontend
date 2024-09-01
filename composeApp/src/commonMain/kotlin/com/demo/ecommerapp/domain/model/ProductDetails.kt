package com.demo.ecommerapp.domain.model

data class ProductDetails(
    val id: Long,
    val sku: String,
    val name: String,
    val description: String,
    val unitPrice: Double,
    val imageUrl: String,
    val active: Boolean,
    val unitsInStock: Int,
)