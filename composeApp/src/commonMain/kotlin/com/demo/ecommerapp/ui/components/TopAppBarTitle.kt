package com.demo.ecommerapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "back arrow",
            modifier = Modifier.clickable { onBackClick() }
        )
//        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}