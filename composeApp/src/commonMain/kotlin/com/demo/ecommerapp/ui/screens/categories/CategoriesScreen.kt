package com.demo.ecommerapp.ui.screens.categories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.LocalPlatformContext
import com.demo.ecommerapp.ui.components.CategoryListView
import com.demo.ecommerapp.ui.theme.appBarTitleTextColor
import com.demo.ecommerapp.ui.theme.backgroundColor
import com.demo.ecommerapp.ui.theme.productSansFamily
import com.dokar.sonner.*
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun CategoriesScreen(
    viewModel: CategoriesListViewModel,
    navigator: Navigator,
) {
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalPlatformContext.current
    val toast = rememberToasterState()

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            TopAppBar(
                contentColor = Color.Black,
                backgroundColor = Color.White,
                content = {
                    Text(
                        text = "Categories List",
                        modifier = Modifier.fillMaxWidth(),
                        color = appBarTitleTextColor,
                        fontFamily = productSansFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            )
        },
        backgroundColor = backgroundColor
    ) {

        when {
            // loading state
            uiState.value.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.value.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.value.error)
                    Napier.e(tag = "CategoriesScreen", message = uiState.value.error)
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    uiState.value.data?.let { categoriesList ->
                        items(categoriesList) {
                            CategoryListView(
                                category = it,
                                onCategoryItemClick = { toast.show(it.categoryName) }
                            )

                            Toaster(state = toast)
                        }
                    }
                }
            }
        }
    }
}