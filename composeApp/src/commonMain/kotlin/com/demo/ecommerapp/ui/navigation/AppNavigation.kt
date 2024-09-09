package com.demo.ecommerapp.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.ui.screens.auth.LoginScreen
import com.demo.ecommerapp.ui.screens.category_details.CategoryDetailsScreen
import com.demo.ecommerapp.ui.screens.category_details.CategoryDetailsViewModel
import com.demo.ecommerapp.ui.screens.home.DashboardScreen
import com.demo.ecommerapp.ui.screens.notification.NotificationListScreen
import com.demo.ecommerapp.ui.screens.product_details.*
import com.demo.ecommerapp.ui.screens.products_list.*
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.*

@Composable
fun AppNavigation() {
    val navigator = rememberNavigator()

    NavHost(
        navigator = navigator,
        initialRoute = NavigationRoute.Login.route,
        swipeProperties = SwipeProperties(spaceToSwipe = 15.dp)
    ) {
        // login screen
        scene(route = NavigationRoute.Login.route) {
            LoginScreen(
                modifier = Modifier,
                navigator = navigator,
                onLoginClick = {
                    // navigate to dashboard screen
                    navigator.navigate(NavigationRoute.Dashboard.route)
                }
            )
        }

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

        // category details
        scene(route = NavigationRoute.CategoryDetails.route) {
            val id = it.path.filter { categoryId -> categoryId.isDigit() }
            val viewModel: CategoryDetailsViewModel = koinViewModel(CategoryDetailsViewModel::class)
            viewModel.getProductsListByCategory(categoryId = id.toLong())
            CategoryDetailsScreen(
                modifier = Modifier,
                viewModel = viewModel,
                navigator = navigator,
                onProductItemClick = {
                    navigator.navigate(NavigationRoute.ProductDetails.getRoute(id = it))
                }
            )
        }

        // notifications
        scene(route = NavigationRoute.Notifications.route) {
            NotificationListScreen(
                navigator = navigator,
                modifier = Modifier
            )
        }
    }
}

sealed class NavigationRoute(val route: String) {
    data object Login : NavigationRoute("/login")
    data object Dashboard : NavigationRoute("/dashboard")
    data object ProductsList : NavigationRoute("/products_list")
    data object ProductDetails : NavigationRoute("/product_details/{id}") {
        fun getRoute(id: Long) = "/product_details/${id}}"
    }
    data object CategoryDetails : NavigationRoute("/category_details/{id}") {
        fun getRoute(id: Long) = "/category_details/${id}}"
    }
    data object Notifications : NavigationRoute("/notifications")
}