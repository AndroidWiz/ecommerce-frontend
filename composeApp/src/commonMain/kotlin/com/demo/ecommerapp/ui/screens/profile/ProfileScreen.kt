package com.demo.ecommerapp.ui.screens.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.ui.theme.*
import ecommerapp.composeapp.resources.*
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.*

@Composable
fun ProfileScreen(
    modifier: Modifier,
    navigator: Navigator,
) {
    Scaffold(
        containerColor = backgroundColor
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                modifier = modifier.padding(end = 10.dp).size(100.dp)
            )

            Spacer(modifier = modifier.height(10.dp))
            // user name
            Text(
                text = "Kelvin Jones",
                fontSize = 16.sp,
                color = primaryTextColor,
                fontWeight = FontWeight.Bold,
                fontFamily = productSansFamily(),
                lineHeight = 20.sp
            )

            Spacer(modifier = modifier.height(20.dp))
            ProfileSectionsView(
                modifier = modifier,
                icon = Res.drawable.personal_information,
                title = "Personal Information"
            )
            ProfileSectionsView(
                modifier = modifier,
                icon = Res.drawable.wishlist,
                title = "Wishlist",
                iconSize = 20.dp
            )
            ProfileSectionsView(
                modifier = modifier,
                icon = Res.drawable.order_history,
                title = "Order History"
            )
            ProfileSectionsView(
                modifier = modifier,
                icon = Res.drawable.payment_methods,
                title = "Payment Methods",
                iconSize = 24.dp
            )
            ProfileSectionsView(
                modifier = modifier,
                icon = Res.drawable.languages,
                title = "Language",
                iconSize = 24.dp
            )
            ProfileSectionsView(
                modifier = modifier,
                icon = Res.drawable.privacy_policy,
                title = "Privacy Policy",
            )
        }
    }
}


@Composable
fun ProfileSectionsView(
    modifier: Modifier,
    icon: DrawableResource,
    title: String,
    iconSize: Dp = 30.dp,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 5.dp)
            .height(50.dp)
            .clickable {},
        shape = RoundedCornerShape(size = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = modifier.fillMaxSize().padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // section icon
            IconButton(
                onClick = { },
                modifier = modifier
                    .background(
                        color = profileSectionIconBgColor,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .size(36.dp)
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = "$title icon",
                    contentScale = ContentScale.Inside,
                    modifier = modifier.size(iconSize)
                )
            }

            // section title
            Text(
                text = title,
                modifier = modifier.weight(1f),
                color = profileSectionTitleTextColor,
                fontFamily = productSansFamily(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
            )

            // go icon
            IconButton(
                onClick = { },
                modifier = modifier.size(18.dp),
                colors = IconButtonDefaults.iconButtonColors(contentColor = profileSectionTitleTextColor),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                    contentDescription = "navigate to $title icon",
                )
            }
        }
    }
}