package com.demo.ecommerapp.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.ui.theme.*

@Composable
fun CustomTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done,
            platformImeOptions = PlatformImeOptions()
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        /*placeholder = {
            Text(
                text = label,
                fontFamily = productSansFamily(),
                color = textFieldLabelColor
            )
        },*/
        label = {
            Text(
                text = label,
                fontFamily = productSansFamily(),
                color = textFieldLabelColor
            )
        },
        singleLine = true,
        textStyle = TextStyle(
//            color = textFieldTextColor,
            fontFamily = productSansFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        interactionSource = interactionSource,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = primaryTextColor,
            unfocusedTextColor = secondaryTextColor,
            focusedContainerColor = textFieldBgColor,
            unfocusedContainerColor = textFieldBgColor,
            focusedBorderColor = textFieldBgColor,
            unfocusedBorderColor = textFieldBgColor
        ),
        shape = RoundedCornerShape(size = 12.dp),
    )
}