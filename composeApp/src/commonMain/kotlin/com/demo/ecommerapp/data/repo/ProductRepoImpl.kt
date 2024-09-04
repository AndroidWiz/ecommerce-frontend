package com.demo.ecommerapp.data.repo

import com.demo.ecommerapp.data.model.ProductsDTO
import com.demo.ecommerapp.data.model.ProductsResponseDTO
import com.demo.ecommerapp.data.utils.Constants
import com.demo.ecommerapp.domain.mappers.toProductDetailsDomain
import com.demo.ecommerapp.domain.mappers.toProductsDomain
import com.demo.ecommerapp.domain.model.ProductDetails
import com.demo.ecommerapp.domain.model.Products
import com.demo.ecommerapp.domain.repo.ProductRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductRepoImpl(private val httpClient: HttpClient) : ProductRepo {

    /*override suspend fun getProducts(): List<Products> {
        return httpClient.get("${Constants.BASE_URL}/products").body<List<ProductsDTO>>()
            .toDomain()
    }*/

    override suspend fun getProducts(): List<Products> {
        return httpClient.get("${Constants.BASE_URL}/products")
//        return httpClient.get("${Constants.BASE_URL}/products?size=100")
            .body<ProductsResponseDTO>()
            ._embedded
            .products
            .toProductsDomain()
    }

    override suspend fun getProductDetails(id: Long): ProductDetails {
        return httpClient.get("${Constants.BASE_URL}/products/${id}").body<ProductsDTO>()
            .toProductDetailsDomain()
    }
}