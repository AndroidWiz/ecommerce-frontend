package com.demo.ecommerapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategoryDTO(
    val id: Long,
    val categoryName: String,
    val products: MutableSet<ProductsDTO> = mutableSetOf(),
)