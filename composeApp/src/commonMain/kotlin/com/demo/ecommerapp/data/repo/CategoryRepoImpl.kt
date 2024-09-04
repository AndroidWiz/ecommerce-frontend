package com.demo.ecommerapp.data.repo

import com.demo.ecommerapp.data.model.ProductCategoryResponseDTO
import com.demo.ecommerapp.data.utils.Constants
import com.demo.ecommerapp.domain.mappers.toCategoriesDomain
import com.demo.ecommerapp.domain.model.ProductCategory
import com.demo.ecommerapp.domain.repo.CategoryRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CategoryRepoImpl(private val httpClient: HttpClient) : CategoryRepo {

    override suspend fun getCategories(): List<ProductCategory> {
        return httpClient.get("${Constants.BASE_URL}/product-category")
            .body<ProductCategoryResponseDTO>()
            ._embedded
            .productCategory
            .toCategoriesDomain()
    }
}