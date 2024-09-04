package com.demo.ecommerapp.data.di

import com.demo.ecommerapp.data.repo.CategoryRepoImpl
import com.demo.ecommerapp.data.repo.ProductRepoImpl
import com.demo.ecommerapp.domain.repo.CategoryRepo
import com.demo.ecommerapp.domain.repo.ProductRepo
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
//                json()
                json(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    factory<ProductRepo> { ProductRepoImpl(get<HttpClient>()) }
    factory<CategoryRepo> { CategoryRepoImpl(get<HttpClient>()) }
}