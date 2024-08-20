package com.demo.ecommerapp.domain.repo

import com.demo.ecommerapp.domain.model.Products

interface ProductRepo {

    suspend fun getProducts(): List<Products>
}