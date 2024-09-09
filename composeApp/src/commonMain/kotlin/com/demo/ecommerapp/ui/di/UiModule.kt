package com.demo.ecommerapp.ui.di

import com.demo.ecommerapp.ui.screens.category_details.CategoryDetailsViewModel
import com.demo.ecommerapp.ui.screens.category_list.CategoriesListViewModel
import com.demo.ecommerapp.ui.screens.product_details.ProductDetailsViewModel
import com.demo.ecommerapp.ui.screens.products_list.ProductsListViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { ProductsListViewModel(get()) }
    factory { ProductDetailsViewModel(get()) }
    factory { CategoriesListViewModel(get()) }
    factory { CategoryDetailsViewModel(get()) }
}