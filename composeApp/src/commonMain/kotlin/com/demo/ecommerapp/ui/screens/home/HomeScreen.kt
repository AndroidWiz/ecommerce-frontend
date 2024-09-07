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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import coil3.compose.LocalPlatformContext
import com.demo.ecommerapp.ui.components.*
import com.demo.ecommerapp.ui.screens.categories.*
import com.demo.ecommerapp.ui.screens.products_list.*
import com.demo.ecommerapp.ui.theme.*
import com.dokar.sonner.*
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
                                shape = RoundedCornerShape(size = 12.dp)
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
            // promotion
            item {
                PromotionView(modifier = modifier)
            }

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
fun PromotionView(
    modifier: Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth().height(140.dp),
        shape = RoundedCornerShape(size = 12.dp),
        backgroundColor = promotionCardBgColor,
        elevation = 5.dp
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.fillMaxWidth(0.66f).fillMaxHeight()
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 16.dp,
//                        end = 6.dp
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Your one stop gadget place!",
                    color = promotionTitleTextColor,
                    fontFamily = productSansFamily(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "We offer the best products for you, nothing but sheer quality.",
                    color = promotionDescTextColor,
                    fontFamily = productSansFamily(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 12.sp
                )
                TextButton(
                    onClick = {},
                    modifier = modifier.width(150.dp),
                    elevation = ButtonDefaults.elevation(3.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.White,
                        contentColor = promotionCardBgColor
                    ),
                ) {
                    Text(
                        text = "Shop Now",
                        fontFamily = productSansFamily(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Image(
                painter = painterResource(Res.drawable.promo_img),
                contentDescription = "promo image",
                modifier = modifier.fillMaxSize()
                    .padding(
                        top = 6.dp,
                        bottom = 6.dp,
                        end = 6.dp
                    ),
                contentScale = ContentScale.FillBounds
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