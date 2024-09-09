package com.demo.ecommerapp.ui.screens.auth

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.demo.ecommerapp.ui.components.*
import com.demo.ecommerapp.ui.theme.*
import ecommerapp.composeapp.resources.*
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginScreen(
    modifier: Modifier,
    navigator: Navigator,
    onLoginClick: () -> Unit,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(contentWindowInsets = ScaffoldDefaults.contentWindowInsets) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            // app logo
            Box(
                modifier = modifier.wrapContentHeight(),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = modifier.padding(20.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.app_logo),
                        contentDescription = "app logo",
                        modifier = Modifier.fillMaxWidth().height(85.dp)
                    )

                    Text(
                        text = "E-Commerce App",
                        modifier = modifier.fillMaxWidth(),
                        color = selectedBottomNavColor,
                        fontFamily = productSansFamily(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // welcome text
            Text(
                text = "Welcome to E-Commerce App",
                modifier = modifier.fillMaxWidth(),
                color = appBarTitleTextColor,
                fontFamily = productSansFamily(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
            Text(
                text = "Please enter your details below to proceed into a wonderful shopping experience.",
                modifier = modifier.fillMaxWidth(),
                color = secondaryTextColor,
                fontFamily = productSansFamily(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )

            // login form
            Spacer(modifier = modifier.height(30.dp))
            // email
            CustomTextField(
                modifier = modifier.padding(),
                value = email,
                onValueChange = { email = it },
                label = "Username or Email",
                keyboardType = KeyboardType.Email,
            )
            Spacer(modifier = modifier.height(10.dp))
            // password
            CustomPasswordField(
                modifier = modifier.padding(),
                value = password,
                onValueChange = { password = it },
                label = "Password",
                keyboardType = KeyboardType.Password,
            )

            // continue button
            TextButton(
                onClick = { onLoginClick() },
                modifier = modifier.fillMaxWidth().height(42.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = when (email.isNotEmpty() && password.isNotEmpty()) {
                        true -> buttonBgColor
                        false -> inactiveButtonBgColor
                    },
                    contentColor = when (email.isNotEmpty() && password.isNotEmpty()) {
                        true -> Color.White
                        false -> secondaryTextColor
                    }
                )
            ) {
                Text(
                    text = "Continue",
                    fontSize = 12.sp,
                    fontFamily = productSansFamily(),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}