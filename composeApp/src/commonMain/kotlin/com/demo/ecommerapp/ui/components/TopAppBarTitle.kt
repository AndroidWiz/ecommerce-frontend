package com.demo.ecommerapp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.ui.theme.*

@Composable
fun TopAppBarTitle(
    title: String,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
            contentDescription = "back arrow",
            modifier = Modifier.clickable { onBackClick() }
        )
//        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            color = appBarTitleTextColor,
            fontFamily = productSansFamily(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}