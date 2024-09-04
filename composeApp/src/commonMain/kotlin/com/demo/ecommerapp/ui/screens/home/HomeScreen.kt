package com.demo.ecommerapp.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import coil3.compose.LocalPlatformContext
import com.demo.ecommerapp.ui.components.ProductsListView
import com.demo.ecommerapp.ui.screens.products_list.ProductsListStateHolder
import com.demo.ecommerapp.ui.screens.products_list.ProductsListViewModel
import com.demo.ecommerapp.ui.theme.*
import com.dokar.sonner.rememberToasterState
import ecommerapp.composeapp.resources.*
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.*

@Composable
fun HomeScreen(
    viewModel: ProductsListViewModel,
    navigator: Navigator,
    onProductItemClick: (Long) -> Unit,
) {

    val uiState = viewModel.uiState.collectAsState()
    val context = LocalPlatformContext.current
    val toast = rememberToasterState()
    val scrollState = rememberScrollState()

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
//            TopSection()
            TopAppBar(
                modifier = Modifier
                    .height(55.dp),
                backgroundColor = Color.White,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 6.dp, end = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TopSection()
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .background(
                                color = notiIconBgColor,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .size(34.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.notification),
                            contentDescription = "notification",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding).fillMaxSize().padding(bottom = 50.dp),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 10.dp,
                top = 5.dp,
                bottom = 5.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { ProductCategoriesView() }
            item {
                HomeProductView(
                    title = "Recommended",
                    uiState = uiState,
                    onProductItemClick = onProductItemClick
                )
            }
            item {
                HomeProductView(
                    title = "Sale Products",
                    uiState = uiState,
                    onProductItemClick = onProductItemClick
                )
            }
        }
    }
}


@Composable
fun TopSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // user image
        /*SubcomposeAsyncImage(
            model = Res.drawable.user_img,
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            contentDescription = "user image",
            modifier = Modifier.size(40.dp).clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )*/

        Image(
            painter = painterResource(Res.drawable.user_img),
            contentDescription = "user image",
            modifier = Modifier.padding(end = 10.dp).size(40.dp)
        )

        Column {
            Text(
                text = "Hello User",
                fontSize = 12.sp,
                color = secondaryTextColor,
                fontWeight = FontWeight.Light,
                fontFamily = productSansFamily(),
                lineHeight = 14.sp
            )
            Text(
                text = "Find your latest product",
                fontSize = 16.sp,
                color = primaryTextColor,
                fontWeight = FontWeight.Bold,
                fontFamily = productSansFamily(),
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun ProductCategoriesView() {
    Text(
        text = "Product Categories",
        fontSize = 16.sp,
        color = primaryTextColor,
        fontWeight = FontWeight.Bold,
        fontFamily = productSansFamily(),
        lineHeight = 18.sp
    )
}

@Composable
fun HomeProductView(
    title: String,
    uiState: State<ProductsListStateHolder>,
    onProductItemClick: (Long) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth().height(460.dp)) {
        // header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // title
            Text(
//                text = "Recommended",
                text = title,
                fontSize = 16.sp,
                color = primaryTextColor,
                fontWeight = FontWeight.Bold,
                fontFamily = productSansFamily(),
                lineHeight = 18.sp
            )

            // see all
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "See all",
                    fontSize = 12.sp,
                    color = secondaryTextColor,
                    fontWeight = FontWeight.Light,
                    fontFamily = productSansFamily(),
                    lineHeight = 14.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .background(
                            color = notiIconBgColor,
                            shape = CircleShape
                        )
                        .size(18.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.right_arrow),
                        contentDescription = "right arrow",
                        modifier = Modifier.size(8.dp),
                        tint = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // products
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
                    Napier.e(tag = "HomeScreen", message = uiState.value.error)
                }
            }

            else -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    uiState.value.data?.let { productsList ->
                        items(productsList.shuffled().take(4)) { // show 4 items randomly
                            ProductsListView(
                                product = it,
                                onProductItemClick = { onProductItemClick(it.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}