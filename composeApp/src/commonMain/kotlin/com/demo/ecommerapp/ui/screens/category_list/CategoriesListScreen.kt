package com.demo.ecommerapp.ui.screens.category_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import coil3.compose.LocalPlatformContext
import com.demo.ecommerapp.ui.components.CategoryListView
import com.demo.ecommerapp.ui.theme.*
import com.dokar.sonner.*
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesListScreen(
    viewModel: CategoriesListViewModel,
    navigator: Navigator,
    modifier: Modifier,
    onCategoryItemClick: (Long) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalPlatformContext.current
    val toast = rememberToasterState()

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Product Categories",
                        modifier = modifier.fillMaxWidth(),
                        color = appBarTitleTextColor,
                        fontFamily = productSansFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = appBarTitleTextColor
                )
            )
        },
        containerColor = backgroundColor
    ) { innerPadding ->

        when {
            // loading state
            uiState.value.isLoading -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.value.error.isNotEmpty() -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.value.error)
                    Napier.e(tag = "CategoriesScreen", message = uiState.value.error)
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = modifier.padding(innerPadding).fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    uiState.value.data?.let { categoriesList ->
                        items(categoriesList) {
                            CategoryListView(
                                category = it,
                                onCategoryItemClick = { onCategoryItemClick(it.id) }
                            )

                            Toaster(state = toast)
                        }
                    }
                }
            }
        }
    }
}