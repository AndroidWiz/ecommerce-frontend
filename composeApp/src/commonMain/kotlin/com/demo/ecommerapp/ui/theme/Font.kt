package com.demo.ecommerapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.*
import ecommerapp.composeapp.resources.*
import org.jetbrains.compose.resources.Font

@Composable
fun productSansFamily() = FontFamily(
    Font(Res.font.ProductSans_Bold, FontWeight.Bold),
    Font(Res.font.ProductSans_Light, FontWeight.Light),
    Font(Res.font.ProductSans_Medium, FontWeight.Medium),
    Font(Res.font.ProductSans_Regular, FontWeight.Normal)
)