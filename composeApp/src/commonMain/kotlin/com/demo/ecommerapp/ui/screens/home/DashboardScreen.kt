package com.demo.ecommerapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Colors
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.ecommerapp.ui.navigation.BottomNavigationRoute
import com.demo.ecommerapp.ui.navigation.NavigationRoute
import com.demo.ecommerapp.ui.screens.cart.CartScreen
import com.demo.ecommerapp.ui.screens.categories.CategoriesListViewModel
import com.demo.ecommerapp.ui.screens.categories.CategoriesScreen
import com.demo.ecommerapp.ui.screens.products_list.ProductsListViewModel
import com.demo.ecommerapp.ui.screens.profile.ProfileScreen
import com.demo.ecommerapp.ui.theme.productSansFamily
import com.demo.ecommerapp.ui.theme.selectedBottomNavColor
import com.demo.ecommerapp.ui.theme.unselectedBottomNavColor
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.painterResource

@Composable
fun DashboardScreen(
    navigator: Navigator,
) {

    var selectedIndex by remember { mutableIntStateOf(0) }
    val bottomNavigationController = rememberNavigator()
    val bottomNavigationItems = listOf(
        BottomNavigationRoute.Home,
        BottomNavigationRoute.Categories,
        BottomNavigationRoute.Cart,
        BottomNavigationRoute.Profile
    )

    var currentRoute by remember { mutableStateOf(BottomNavigationRoute.Home.route) }

    Surface() {
        Scaffold(
            contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
            bottomBar = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    BottomNavigation(
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = Color.White
                    ) {
                        bottomNavigationItems.forEachIndexed { index, screen ->
                            BottomNavigationItem(
                                selected = selectedIndex == index,
                                onClick = {
                                    selectedIndex = index
                                    bottomNavigationController.navigate(
                                        screen.route,
                                        options = NavOptions(launchSingleTop = true)
                                    )
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(screen.icon),
                                        contentDescription = screen.label,
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = screen.label,
                                        fontFamily = productSansFamily(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        textAlign = TextAlign.Center,
                                        lineHeight = 12.sp
                                    )
                                },
                                selectedContentColor = selectedBottomNavColor,
                                unselectedContentColor = unselectedBottomNavColor
                            )
                        }
                    }
                }
            }
        ) {
            NavHost(
                navigator = bottomNavigationController,
                initialRoute = BottomNavigationRoute.Home.route
            ) {
                // home screen
                scene(route = BottomNavigationRoute.Home.route) {
                    currentRoute = BottomNavigationRoute.Home.route
                    val viewModel: ProductsListViewModel = koinViewModel(ProductsListViewModel::class)
                    HomeScreen(
                        viewModel = viewModel,
                        navigator = navigator,
                        modifier = Modifier,
                        onProductItemClick = {
                            // navigate to product details screen
                            navigator.navigate(NavigationRoute.ProductDetails.getRoute(id = it))
                        },
                        onSeeAllClick = {
                            // navigate to all products list
                            navigator.navigate(NavigationRoute.ProductsList.route)
                        }
                    )
                }
                // categories screen
                scene(route = BottomNavigationRoute.Categories.route) {
                    currentRoute = BottomNavigationRoute.Categories.route
                    val viewModel: CategoriesListViewModel = koinViewModel(CategoriesListViewModel::class)
                    CategoriesScreen(
                        viewModel = viewModel,
                        navigator = navigator
                    )
                }
                // cart screen
                scene(route = BottomNavigationRoute.Cart.route) {
                    currentRoute = BottomNavigationRoute.Cart.route
                    CartScreen()
                }
                // profile screen
                scene(route = BottomNavigationRoute.Profile.route) {
                    currentRoute = BottomNavigationRoute.Profile.route
                    ProfileScreen()
                }
            }
        }
    }
}