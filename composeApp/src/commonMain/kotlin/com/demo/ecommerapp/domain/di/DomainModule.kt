package com.demo.ecommerapp.domain.di

import com.demo.ecommerapp.domain.use_cases.*
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsListUseCase() }
    factory { GetProductDetailsUseCase() }
    factory { GetCategoryListUseCase() }
    factory { GetProductsListByCategoryUseCase() }
}