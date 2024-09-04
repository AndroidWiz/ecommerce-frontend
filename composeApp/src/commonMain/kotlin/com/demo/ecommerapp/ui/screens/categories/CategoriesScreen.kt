package com.demo.ecommerapp.ui.screens.categories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.LocalPlatformContext
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
                    Napier.e(tag = "CategoriesScreen", message = uiState.value.error)
                }
            }

            else -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    uiState.value.data?.let { categoriesList ->
                        items(categoriesList) {
                            Text(text = it.categoryName)

                            Toaster(state = toast)
                        }
                    }
                }
            }
        }
    }
}