package com.demo.ecommerapp.domain.mappers

import com.demo.ecommerapp.data.model.*
import com.demo.ecommerapp.data.utils.Constants
import com.demo.ecommerapp.domain.model.ProductDetails
import com.demo.ecommerapp.domain.model.Products

fun List<ProductsDTO>.toDomain(): List<Products> = map {
    Products(
        id = it.id,
        name = it.name,
        unitPrice = it.unitPrice,
        imageUrl = "${Constants.IMAGE_URL_PREFIX}${it.imageUrl}",
    )
}

fun ProductsDTO.toDomain(): ProductDetails {
    return ProductDetails(
        id = this.id,
        sku = this.sku,
        name = this.name,
        description = this.description,
        unitPrice = this.unitPrice,
        imageUrl = "${Constants.IMAGE_URL_PREFIX}${this.imageUrl}",
        active = this.active,
        unitsInStock = this.unitsInStock
    )
}