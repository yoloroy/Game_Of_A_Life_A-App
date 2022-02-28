package com.yoloroy.gameoflife.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Login
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
fun LoginScreen(callback: LoginCallback, moveToRegister: () -> Unit) {
    var emailOrLogin by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "GOAL",
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 72.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                // header
                Row {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // email or login
                OutlinedTextField(
                    value = emailOrLogin,
                    onValueChange = { emailOrLogin = it },
                    label = { Text("Email or Login") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                // password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    trailingIcon = {
                        val image = if (isPasswordVisible)
                            Icons.Rounded.Visibility
                        else
                            Icons.Rounded.VisibilityOff

                        IconButton(onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }) {
                            Icon(imageVector = image, "")
                        }
                    },
                    visualTransformation =
                        if (isPasswordVisible) VisualTransformation.None
                        else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                // to sign up
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(
                            style = MaterialTheme.typography.body1.toSpanStyle()
                                    + SpanStyle(color = MaterialTheme.colors.onBackground)
                        ) {
                            append("Don't have an account? ")
                        }
                        withStyle(
                            style = MaterialTheme.typography.body1.toSpanStyle()
                                    + SpanStyle(color = MaterialTheme.colors.secondary)
                        ) {
                            append("Sign up!")
                        }
                    },
                    onClick = { moveToRegister() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { callback.login(emailOrLogin, password) }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Login,
                    tint = MaterialTheme.colors.background,
                    contentDescription = "Sign in"
                )
            }
        }
    )
}

@Preview
@Composable
fun LoginPreview() {
    GameOfLifeTheme {
        Surface(
            Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginScreen({ _, _ -> }, {})
        }
    }
}
