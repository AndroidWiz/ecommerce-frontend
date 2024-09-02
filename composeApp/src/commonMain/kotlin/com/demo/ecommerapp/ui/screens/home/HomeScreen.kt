package com.demo.ecommerapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.ui.theme.notiIconBgColor
import com.demo.ecommerapp.ui.theme.primaryTextColor
import com.demo.ecommerapp.ui.theme.productSansFamily
import com.demo.ecommerapp.ui.theme.secondaryTextColor
import ecommerapp.composeapp.resources.*
import org.jetbrains.compose.resources.*

@Composable
fun HomeScreen() {
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
    ) {
        Column {

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
                lineHeight = 15.sp
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