package com.demo.ecommerapp.ui.screens.products_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.LocalPlatformContext
import com.demo.ecommerapp.ui.components.ProductsListView
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ProductsListScreen(
    viewModel: ProductsListViewModel,
    navigator: Navigator,
    onProductItemClick: (Long) -> Unit,
) {

    val uiState = viewModel.uiState.collectAsState()
    val context = LocalPlatformContext.current
    val toast = rememberToasterState()

    Scaffold(contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            TopAppBar(
                contentColor = Color.Black,
                backgroundColor = Color.White,
                content = {
                    Text(
                        text = "Products List",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
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
                    Napier.e(tag = "ProductsListScreen", message = uiState.value.error)
                }
            }

            else -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
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