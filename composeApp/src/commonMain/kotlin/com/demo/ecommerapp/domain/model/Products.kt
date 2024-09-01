package com.demo.ecommerapp.domain.model

data class Products(
    val id: Long,
    val name: String,
    val unitPrice: Double,
    val imageUrl: String,
)