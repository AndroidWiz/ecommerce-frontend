package com.demo.ecommerapp.ui.navigation

import androidx.compose.runtime.Composable
import com.demo.ecommerapp.ui.screens.products_list.ProductsListScreen
import com.demo.ecommerapp.ui.screens.products_list.ProductsListViewModel
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun AppNavigation() {
    val navigator = rememberNavigator()

    NavHost(navigator = navigator, initialRoute = NavigationRoute.ProductsList.route) {
        scene(route = NavigationRoute.ProductsList.route) {
            val viewModel: ProductsListViewModel = koinViewModel(ProductsListViewModel::class)
            ProductsListScreen(
                viewModel = viewModel,
                navigator = navigator
            )
        }
    }
}

sealed class NavigationRoute(val route: String) {
    data object ProductsList : NavigationRoute("/products_list")
    data object ProductDetails : NavigationRoute("/product_details/{id}") {
        fun getRoute(id: Int) = "/product_details/${id}}"
    }
}