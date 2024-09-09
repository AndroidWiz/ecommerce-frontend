package com.demo.ecommerapp.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.ui.theme.*

@Composable
fun CustomPasswordField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Password,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

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
        label = {
            Text(
                text = label,
                fontFamily = productSansFamily(),
                color = textFieldLabelColor
            )
        },
        singleLine = true,
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val iconVisibility =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordHidden) "Show password" else "Hide password"
                Icon(
                    imageVector = iconVisibility,
                    contentDescription = description,
                    tint = passwordFieldIconColor
                )
            }
        },
        textStyle = TextStyle(
//            color = textFieldTextColor,
            fontFamily = productSansFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
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