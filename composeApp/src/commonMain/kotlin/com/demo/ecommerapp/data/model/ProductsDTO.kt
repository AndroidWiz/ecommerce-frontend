package com.demo.ecommerapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductsDTO(
//    val id: Long,
    val sku: String,
    val name: String,
    val description: String,
    val unitPrice: Double,
    val imageUrl: String,
    val active: Boolean,
    val unitsInStock: Int,
    val dateCreated: String?,
    val lastUpdated: String?,
//    val category: ProductCategoryDTO,
)

@Serializable
data class ProductsResponseDTO(
    val _embedded: EmbeddedProductsDTO
)

@Serializable
data class EmbeddedProductsDTO(
    val products: List<ProductsDTO>
)