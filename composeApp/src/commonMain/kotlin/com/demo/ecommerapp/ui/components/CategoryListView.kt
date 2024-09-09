package com.demo.ecommerapp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.domain.model.ProductCategory
import com.demo.ecommerapp.ui.theme.*

@Composable
fun CategoryListView(
    category: ProductCategory,
    onCategoryItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .wrapContentHeight()
            .clickable {
                onCategoryItemClick()
            },
        shape = RoundedCornerShape(size = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp),
        border = BorderStroke(
            width = 0.35.dp,
            color = cardBorderColor
        )
    ) {
        // name
        Text(
            text = category.categoryName,
            modifier = Modifier.padding(10.dp),
            color = primaryTextColor,
            fontFamily = productSansFamily(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
    }
}