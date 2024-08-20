package com.demo.ecommerapp.ui.screens.products_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import io.github.aakira.napier.Napier
import io.ktor.util.logging.Logger
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ProductsListScreen(
    viewModel: ProductsListViewModel,
    navigator: Navigator,
) {

    val uiState = viewModel.uiState.collectAsState()
    val context = LocalPlatformContext.current
    val toast = rememberToasterState()

    Scaffold(contentWindowInsets = ScaffoldDefaults.contentWindowInsets) {

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
                            Column(
                                modifier = Modifier.fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(10.dp)
                                    .clickable {
                                        toast.show(message = it.name, type = ToastType.Normal)
                                    }
                            ) {
                                // image
                                SubcomposeAsyncImage(
                                    model = it.imageUrl,
                                    loading = {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator()
                                        }
                                    },
                                    contentDescription = "product image",
                                    modifier = Modifier.fillMaxWidth().height(160.dp)
                                )

                                // name
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.body2,
                                    fontWeight = FontWeight.Medium,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 2,
                                    textAlign = TextAlign.Center
                                )
                            }

                            Toaster(state = toast)
                        }
                    }
                }
            }
        }
    }
}