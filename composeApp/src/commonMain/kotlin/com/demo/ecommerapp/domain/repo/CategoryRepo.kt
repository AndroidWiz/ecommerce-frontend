package com.demo.ecommerapp.domain.repo

import com.demo.ecommerapp.domain.model.ProductCategory

interface CategoryRepo {

    suspend fun getCategories(): List<ProductCategory>

}