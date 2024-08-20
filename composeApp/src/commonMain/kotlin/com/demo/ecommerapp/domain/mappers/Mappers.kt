package com.demo.ecommerapp.domain.mappers

import com.demo.ecommerapp.data.model.ProductsDTO
import com.demo.ecommerapp.domain.model.Products

fun List<ProductsDTO>.toDomain(): List<Products> = map {
    Products(
//        id = it.id,
        sku = it.sku,
        name = it.name,
        description = it.description,
        unitPrice = it.unitPrice,
        imageUrl = it.imageUrl,
        active = it.active,
        unitsInStock = it.unitsInStock,
    )
}
