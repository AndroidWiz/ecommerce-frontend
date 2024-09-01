package com.demo.ecommerapp.ui.screens.product_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.demo.ecommerapp.ui.components.TopAppBarTitle
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel,
    navigator: Navigator,
) {

    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            TopAppBar(
                contentColor = Color.Black,
                backgroundColor = Color.White
            ) {
                TopAppBarTitle(
                    title = "Product Details",
                    onBackClick = { navigator.goBack() }
                )
            }
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
                    Napier.i(
                        tag = "ProductDetailsScreen",
                        message = uiState.value.isLoading.toString()
                    )
                }
            }

            uiState.value.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.value.error,
                        color = Color.Red
                    )
                    Napier.e(tag = "ProductDetailsScreen", message = uiState.value.error)
                }
            }

            else -> {
                Column(
                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                        .padding(10.dp)
                ) {
                    uiState.value.data?.let { productDetails ->
                        Napier.d(tag = "ProductDetailsScreen", message = productDetails.toString())
                        SubcomposeAsyncImage(
                            model = productDetails.imageUrl,
                            loading = {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            },
                            alignment = Alignment.CenterStart,
                            contentDescription = "product details image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // title
                        Text(
                            text = productDetails.name,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // price
                        Text(
                            text = "$ ${productDetails.unitPrice}",
                            color = Color.Magenta,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Justify,
                            lineHeight = 12.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // add to cart button
                        TextButton(
                            onClick = {},
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.textButtonColors(Color.Magenta)
                        ) {
                            Text(text = "Add to cart", color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        // description
                        Text(
                            text = productDetails.description,
                            style = MaterialTheme.typography.body2
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }

}