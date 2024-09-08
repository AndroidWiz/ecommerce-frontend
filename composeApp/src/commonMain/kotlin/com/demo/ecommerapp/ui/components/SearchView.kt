package com.demo.ecommerapp.ui.components

//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.MaterialTheme.colors
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.demo.ecommerapp.ui.theme.productSansFamily
//import com.demo.ecommerapp.ui.theme.secondaryTextColor
//import ecommerapp.composeapp.resources.Res
//import ecommerapp.composeapp.resources.search
//import org.jetbrains.compose.resources.painterResource
//
//@Composable
//fun SearchView() {
//    TextField(
//        value = "",
//        onValueChange = {},
//        placeholder = {
//            Text(
//                text = "Search",
//                fontSize = 12.sp,
//                color = secondaryTextColor,
//                maxLines = 1,
//                fontFamily = productSansFamily(),
//                fontWeight = FontWeight.Light
//            )
//        },
//        modifier = Modifier
//            .fillMaxWidth().background(color = Color.Transparent),
//        leadingIcon = {
//            Icon(painter = painterResource(Res.drawable.search), contentDescription = "Search")
//        },
//        shape = RoundedCornerShape(size = 12.dp),
//        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
//    )
//}