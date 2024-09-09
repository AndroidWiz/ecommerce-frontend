package com.demo.ecommerapp.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.ui.navigation.*
import com.demo.ecommerapp.ui.screens.cart.CartScreen
import com.demo.ecommerapp.ui.screens.category_list.*
import com.demo.ecommerapp.ui.screens.products_list.ProductsListViewModel
import com.demo.ecommerapp.ui.screens.profile.ProfileScreen
import com.demo.ecommerapp.ui.theme.*
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun DashboardScreen(
    navigator: Navigator,
    modifier: Modifier,
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

    Surface {
        Scaffold(
            contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
            bottomBar = {
                Box(modifier = modifier.fillMaxWidth().height(55.dp)) {
                    NavigationBar(
                        modifier = modifier.fillMaxWidth(),
                        containerColor = Color.White
                    ) {
                        bottomNavigationItems.forEachIndexed { index, screen ->
                            NavigationBarItem(
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
                                        modifier = modifier.size(18.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = screen.label,
                                        fontFamily = productSansFamily(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        textAlign = TextAlign.Center,
                                        lineHeight = 10.sp
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = selectedBottomNavColor,
                                    selectedTextColor = selectedBottomNavColor,
                                    unselectedIconColor = unselectedBottomNavColor,
                                    unselectedTextColor = unselectedBottomNavColor,
                                    indicatorColor = Color.Transparent
                                )
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
                    val productViewModel: ProductsListViewModel = koinViewModel(ProductsListViewModel::class)
                    val categoryViewModel: CategoriesListViewModel = koinViewModel(CategoriesListViewModel::class)
                    HomeScreen(
                        productViewModel = productViewModel,
                        categoryViewModel = categoryViewModel,
                        navigator = navigator,
                        modifier = Modifier,
                        onProductItemClick = {
                            // navigate to product details screen
                            navigator.navigate(NavigationRoute.ProductDetails.getRoute(id = it))
                        },
                        onSeeAllClick = {
                            // navigate to all products list
                            navigator.navigate(NavigationRoute.ProductsList.route)
                        },
                        onCategoryItemClick = {
                            // navigate to all products list
                            navigator.navigate(NavigationRoute.CategoryDetails.getRoute(id = it))
                        },
                        onNotificationClick = {
                            // navigate to notification screen
                            navigator.navigate(NavigationRoute.Notifications.route)
                        }
                    )
                }
                // categories screen
                scene(route = BottomNavigationRoute.Categories.route) {
                    currentRoute = BottomNavigationRoute.Categories.route
                    val viewModel: CategoriesListViewModel = koinViewModel(CategoriesListViewModel::class)
                    CategoriesListScreen(
                        viewModel = viewModel,
                        navigator = navigator,
                        modifier = Modifier,
                        onCategoryItemClick = {
                            // navigate to all products list
                            navigator.navigate(NavigationRoute.CategoryDetails.getRoute(id = it))
                        }
                    )
                }
                // cart screen
                scene(route = BottomNavigationRoute.Cart.route) {
                    currentRoute = BottomNavigationRoute.Cart.route
                    CartScreen(
                        modifier = Modifier,
                    )
                }
                // profile screen
                scene(route = BottomNavigationRoute.Profile.route) {
                    currentRoute = BottomNavigationRoute.Profile.route
                    ProfileScreen(
                        modifier = Modifier,
                        navigator = navigator
                    )
                }
            }
        }
    }
}