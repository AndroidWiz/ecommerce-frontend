package com.demo.ecommerapp.ui.di

import com.demo.ecommerapp.ui.screens.product_details.ProductDetailsViewModel
import com.demo.ecommerapp.ui.screens.products_list.ProductsListViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { ProductsListViewModel(get()) }
    factory { ProductDetailsViewModel(get()) }
}