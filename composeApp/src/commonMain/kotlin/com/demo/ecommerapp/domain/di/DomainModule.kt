package com.demo.ecommerapp.domain.di

import com.demo.ecommerapp.domain.use_cases.GetProductDetailsUseCase
import com.demo.ecommerapp.domain.use_cases.GetProductsListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsListUseCase() }
    factory { GetProductDetailsUseCase() }
}