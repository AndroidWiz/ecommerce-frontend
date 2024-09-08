package com.demo.ecommerapp.ui.screens.product_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import coil3.compose.SubcomposeAsyncImage
import com.demo.ecommerapp.ui.theme.*
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel,
    navigator: Navigator,
    modifier: Modifier,
) {

    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Product Details",
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
                    Napier.i(
                        tag = "ProductDetailsScreen",
                        message = uiState.value.isLoading.toString()
                    )
                }
            }

            uiState.value.error.isNotEmpty() -> {
                Box(
                    modifier = modifier.fillMaxSize(),
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
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(10.dp)
                ) {
                    uiState.value.data?.let { productDetails ->
                        Napier.d(tag = "ProductDetailsScreen", message = productDetails.toString())
                        // image
                        SubcomposeAsyncImage(
                            model = productDetails.imageUrl,
                            loading = {
                                Box(
                                    modifier = modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            },
                            alignment = Alignment.CenterStart,
                            contentDescription = "product details image",
                            modifier = modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )
                        Spacer(modifier = modifier.height(10.dp))

                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                // title
                                Text(
                                    text = productDetails.name,
                                    color = productTitleTextColor,
                                    fontSize = 16.sp,
                                    fontFamily = productSansFamily(),
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 16.sp
                                )
                                Text(
                                    text = "Product category",
                                    color = secondaryTextColor,
                                    fontSize = 12.sp,
                                    fontFamily = productSansFamily(),
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 14.sp
                                )
                            }

                            // price
                            Text(
                                text = "$ ${productDetails.unitPrice}",
                                color = productPriceTextColor,
                                fontSize = 16.sp,
                                fontFamily = productSansFamily(),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Justify
                            )
                        }
                        Spacer(modifier = modifier.height(10.dp))

                        Column {
                            Text(
                                text = "Product Details",
                                color = productTitleTextColor,
                                fontSize = 16.sp,
                                fontFamily = productSansFamily(),
                                fontWeight = FontWeight.Bold,
                            )
                            // description
                            Text(
                                text = productDetails.description,
                                color = secondaryTextColor,
                                fontSize = 12.sp,
                                fontFamily = productSansFamily(),
                                fontWeight = FontWeight.Normal,
                                lineHeight = 14.sp
                            )
                        }
                        Spacer(modifier = modifier.height(10.dp))

                        // add to cart button
                        TextButton(
                            onClick = {},
                            modifier = modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = buttonBgColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Add to cart",
                                modifier = modifier.padding(5.dp),
                                fontSize = 12.sp,
                                fontFamily = productSansFamily(),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

}