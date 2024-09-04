package com.demo.ecommerapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategoryDTO(
    val id: Long,
    val categoryName: String,
    val products: MutableSet<ProductsDTO> = mutableSetOf()
)

@Serializable
data class ProductCategoryResponseDTO(
    val _embedded: EmbeddedProductCategoryDTO
)

@Serializable
data class EmbeddedProductCategoryDTO(
    val productCategory: List<ProductCategoryDTO>
)