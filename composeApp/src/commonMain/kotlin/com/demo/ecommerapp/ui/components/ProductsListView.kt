package com.demo.ecommerapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.demo.ecommerapp.domain.model.Products
import com.demo.ecommerapp.ui.theme.homeProductPriceTextColor
import com.demo.ecommerapp.ui.theme.homeProductTitleTextColor
import com.demo.ecommerapp.ui.theme.productSansFamily

@Composable
fun ProductsListView(
    product: Products,
    onProductItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(200.dp)
            .clickable {
                onProductItemClick()
            },
        shape = RoundedCornerShape(size = 8.dp),
//        backgroundColor = Color.LightGray,
        backgroundColor = Color.White,
        elevation = 3.dp,
        border = BorderStroke(
            width = 1.dp,
            color = Color.DarkGray
        )
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = 5.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            // image
            SubcomposeAsyncImage(
                model = product.imageUrl,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                },
                contentDescription = "product image",
                modifier = Modifier.fillMaxWidth().height(140.dp),
                contentScale = ContentScale.Fit,
            )

            // name
            Text(
                text = product.name,
                color = homeProductTitleTextColor,
                fontSize = 14.sp,
                fontFamily = productSansFamily(),
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // price
                Text(
                    text = "$ ${product.unitPrice}",
                    color = homeProductPriceTextColor,
                    fontFamily = productSansFamily(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Justify,
                    lineHeight = 13.sp
                )

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ShoppingCart,
                        contentDescription = "Add to cart",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}