package com.demo.ecommerapp.domain.model

data class ProductCategory(
    val id: Long,
    val categoryName: String,
    val products: MutableSet<Products>,
)