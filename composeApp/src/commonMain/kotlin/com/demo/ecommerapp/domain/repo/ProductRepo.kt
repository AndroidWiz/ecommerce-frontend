package com.demo.ecommerapp.domain.repo

import com.demo.ecommerapp.domain.model.ProductDetails
import com.demo.ecommerapp.domain.model.Products

interface ProductRepo {

    suspend fun getProducts(): List<Products>

    suspend fun getProductDetails(id: Long): ProductDetails

    suspend fun getProductsByCategoryId(categoryId: Long): List<Products>
}