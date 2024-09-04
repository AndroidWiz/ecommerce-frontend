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
import com.demo.ecommerapp.ui.components.CategoryListView
import com.demo.ecommerapp.ui.components.ProductsListView
import com.demo.ecommerapp.ui.screens.categories.CategoriesListStateHolder
import com.demo.ecommerapp.ui.screens.categories.CategoriesListViewModel
import com.demo.ecommerapp.ui.screens.products_list.ProductsListStateHolder
import com.demo.ecommerapp.ui.screens.products_list.ProductsListViewModel
import com.demo.ecommerapp.ui.theme.*
import com.dokar.sonner.Toast
import com.dokar.sonner.Toaster
import com.dokar.sonner.ToasterState
import com.dokar.sonner.rememberToasterState
import ecommerapp.composeapp.resources.*
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.*

@Composable
fun HomeScreen(
    productViewModel: ProductsListViewModel,
    categoryViewModel: CategoriesListViewModel,
    navigator: Navigator,
    modifier: Modifier,
    onProductItemClick: (Long) -> Unit,
    onSeeAllClick: () -> Unit,
) {

    val productUiState = productViewModel.uiState.collectAsState()
    val categoryUiState = categoryViewModel.uiState.collectAsState()
    val context = LocalPlatformContext.current
    val toast = rememberToasterState()
    val scrollState = rememberScrollState()

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            TopAppBar(
                modifier = modifier
                    .height(55.dp),
                backgroundColor = Color.White,
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(start = 6.dp, end = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TopSection(modifier = modifier)
                    IconButton(
                        onClick = { },
                        modifier = modifier
                            .background(
                                color = notiIconBgColor,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .size(34.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.notification),
                            contentDescription = "notification",
                            modifier = modifier.size(16.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        },
        backgroundColor = backgroundColor
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(bottom = 50.dp),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 10.dp,
                top = 5.dp,
                bottom = 5.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // product category list
            item {
                ProductCategoriesView(
                    modifier = modifier,
                    uiState = categoryUiState,
                    toast = toast
                )
            }

            // recommended products
            item {
                HomeProductView(
                    title = "Recommended",
                    modifier = modifier,
                    uiState = productUiState,
                    onProductItemClick = onProductItemClick,
                    onSeeAllClick = onSeeAllClick
                )
            }

            // sale products
            item {
                HomeProductView(
                    title = "Sale Products",
                    modifier = modifier,
                    uiState = productUiState,
                    onProductItemClick = onProductItemClick,
                    onSeeAllClick = onSeeAllClick
                )
            }
        }
    }
}


@Composable
fun TopSection(
    modifier: Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // user image
        /*SubcomposeAsyncImage(
            model = Res.drawable.user_img,
            loading = {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            contentDescription = "user image",
            modifier = modifier.size(40.dp).clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )*/

        Image(
            painter = painterResource(Res.drawable.user_img),
            contentDescription = "user image",
            modifier = modifier.padding(end = 10.dp).size(40.dp)
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
fun ProductCategoriesView(
    modifier: Modifier,
    uiState: State<CategoriesListStateHolder>,
    toast: ToasterState,
) {
    Column(modifier = modifier.fillMaxWidth().height(85.dp)) {
        Text(
            text = "Product Categories",
            fontSize = 16.sp,
            color = primaryTextColor,
            fontWeight = FontWeight.Bold,
            fontFamily = productSansFamily(),
            lineHeight = 18.sp
        )

        Spacer(modifier = modifier.height(5.dp))

        // categories
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
                LazyHorizontalGrid(rows = GridCells.Fixed(1)) {
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


@Composable
fun HomeProductView(
    title: String,
    modifier: Modifier,
    uiState: State<ProductsListStateHolder>,
    onProductItemClick: (Long) -> Unit,
    onSeeAllClick: () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth().height(460.dp)) {
        // header
        Row(
            modifier = modifier.fillMaxWidth(),
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
                    modifier = modifier.clickable { onSeeAllClick() },
                    fontSize = 12.sp,
                    color = secondaryTextColor,
                    fontWeight = FontWeight.Light,
                    fontFamily = productSansFamily(),
                    lineHeight = 14.sp
                )
                Spacer(modifier = modifier.width(5.dp))
                IconButton(
                    onClick = { onSeeAllClick() },
                    modifier = modifier
                        .background(
                            color = notiIconBgColor,
                            shape = CircleShape
                        )
                        .size(18.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.right_arrow),
                        contentDescription = "right arrow",
                        modifier = modifier.size(8.dp),
                        tint = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = modifier.height(10.dp))

        // products
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