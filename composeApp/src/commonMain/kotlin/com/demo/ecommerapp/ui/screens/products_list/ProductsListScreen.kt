package com.demo.ecommerapp.ui.screens.products_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.LocalPlatformContext
import com.demo.ecommerapp.ui.components.*
import com.demo.ecommerapp.ui.theme.appBarTitleTextColor
import com.demo.ecommerapp.ui.theme.productSansFamily
import com.dokar.sonner.*
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsListScreen(
    viewModel: ProductsListViewModel,
    navigator: Navigator,
    modifier: Modifier,
    onProductItemClick: (Long) -> Unit,
) {

    val uiState = viewModel.uiState.collectAsState()
    val context = LocalPlatformContext.current
    val toast = rememberToasterState()

    Scaffold(contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Products List",
                        modifier = modifier.fillMaxWidth(),
                        color = appBarTitleTextColor,
                        fontFamily = productSansFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
                        contentDescription = "back arrow",
                        modifier = Modifier.clickable { navigator.goBack() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = appBarTitleTextColor
                )
            )
        }
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
                    Napier.e(tag = "ProductsListScreen", message = uiState.value.error)
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = modifier.padding(innerPadding).fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                ) {
                    uiState.value.data?.let { productsList ->
                        items(productsList) {
                            ProductsListView(
                                product = it,
                                onProductItemClick = { onProductItemClick(it.id) }
                            )

                            Toaster(state = toast)
                        }
                    }
                }
            }
        }
    }
}