package com.demo.ecommerapp.ui.screens.cart

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.ui.theme.*
import ecommerapp.composeapp.resources.Res
import ecommerapp.composeapp.resources.empty_cart
import ecommerapp.composeapp.resources.user_img
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    modifier: Modifier,
) {
    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cart",
                        modifier = modifier.fillMaxWidth(),
                        color = appBarTitleTextColor,
                        fontFamily = productSansFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
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
        Column(
            modifier = modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = modifier.height(100.dp))
            Image(
                painter = painterResource(Res.drawable.empty_cart),
                contentDescription = "user image",
                modifier = modifier.width(230.dp).height(190.dp),
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "Looks like there’s nothing in your cart. Start selecting items to start your shopping experience.",
                modifier = modifier.padding(horizontal = 20.dp).fillMaxWidth(),
                color = secondaryTextColor,
                fontFamily = productSansFamily(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 13.sp
            )
            Spacer(modifier = modifier.height(20.dp))
            // start shopping button
            TextButton(
                onClick = {},
                modifier = modifier.padding(horizontal = 20.dp).fillMaxWidth().height(42.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = buttonBgColor,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Start Shopping",
                    fontSize = 12.sp,
                    fontFamily = productSansFamily(),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}