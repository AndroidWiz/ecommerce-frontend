package com.demo.ecommerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.demo.ecommerapp.ui.screens.home.DashboardScreen
import com.demo.ecommerapp.ui.screens.product_details.ProductDetailsScreen
import com.demo.ecommerapp.ui.screens.product_details.ProductDetailsViewModel
import com.demo.ecommerapp.ui.screens.products_list.ProductsListScreen
import com.demo.ecommerapp.ui.screens.products_list.ProductsListViewModel
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun AppNavigation() {
    val navigator = rememberNavigator()

    NavHost(navigator = navigator, initialRoute = NavigationRoute.Dashboard.route) {
        // dashboard screen
        scene(route = NavigationRoute.Dashboard.route) {
            DashboardScreen(
                navigator = navigator,
                modifier = Modifier
            )
        }
        // products list
        scene(route = NavigationRoute.ProductsList.route) {
            val viewModel: ProductsListViewModel = koinViewModel(ProductsListViewModel::class)
            ProductsListScreen(
                viewModel = viewModel,
                navigator = navigator,
                modifier = Modifier
            ) {
                navigator.navigate(NavigationRoute.ProductDetails.getRoute(id = it))
            }
        }

        // product details
        scene(route = NavigationRoute.ProductDetails.route) {
            val id = it.path.filter { productId -> productId.isDigit() }
            val viewModel: ProductDetailsViewModel = koinViewModel(ProductDetailsViewModel::class)
            viewModel.getProductDetails(id = id.toLong())
            ProductDetailsScreen(
                viewModel = viewModel,
                navigator = navigator,
                modifier = Modifier
            )
        }
    }
}

sealed class NavigationRoute(val route: String) {
    data object Dashboard : NavigationRoute("/dashboard")
    data object ProductsList : NavigationRoute("/products_list")
    data object ProductDetails : NavigationRoute("/product_details/{id}") {
        fun getRoute(id: Long) = "/product_details/${id}}"
    }
}