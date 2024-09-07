package com.demo.ecommerapp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.unit.*
import coil3.compose.SubcomposeAsyncImage
import com.demo.ecommerapp.domain.model.Products
import com.demo.ecommerapp.ui.theme.*

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
        shape = RoundedCornerShape(size = 12.dp),
        backgroundColor = Color.White,
        elevation = 3.dp,
        border = BorderStroke(
            width = 0.35.dp,
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
        )
        {
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
                color = productTitleTextColor,
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
                    color = productPriceTextColor,
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