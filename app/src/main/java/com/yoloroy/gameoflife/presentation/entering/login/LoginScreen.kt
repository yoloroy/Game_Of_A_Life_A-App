package com.yoloroy.gameoflife.presentation.entering.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Login
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yoloroy.gameoflife.presentation.components.TextFieldWithErrorText
import com.yoloroy.gameoflife.presentation.components.TrailingIcon
import com.yoloroy.gameoflife.presentation.ui.icons.VisibilityToggle
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.util.Screen

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    with(viewModel) {
        val emailOrLogin by viewModel.emailOrLogin.observeAsState()
        val password by viewModel.password.observeAsState()

        LoginScreen(
            emailOrLogin = emailOrLogin!!,
            password = password!!,
            onEmailOrLoginUpdate = ::updateEmailOrLogin,
            onPasswordUpdate = ::updatePassword,
            onClickSignIn = {
                signIn(
                    onSuccess = { navController.navigate(Screen.DreamsScreen.route) },
                    onFailure = { /*TODO*/ }
                )
            },
            moveToRegister = { navController.navigate(Screen.RegistrationScreen.route) }
        )
    }
}

@Composable
fun LoginScreen(
    emailOrLogin: String,
    password: String,
    onEmailOrLoginUpdate: (String) -> Unit = {},
    onPasswordUpdate: (String) -> Unit = {},
    onClickSignIn: () -> Unit = {},
    moveToRegister: () -> Unit = {}
) {
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
                Spacer(modifier = Modifier.height(20.dp))

                // email or login
                TextFieldWithErrorText( // TODO error
                    value = emailOrLogin,
                    onValueChange = onEmailOrLoginUpdate,
                    label = "Email or Login",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))

                // password
                TextFieldWithErrorText( // TODO error
                    value = password,
                    onValueChange = onPasswordUpdate,
                    label = "Password",
                    trailingIcon = TrailingIcon(
                        image = Icons.Sharp.VisibilityToggle[isPasswordVisible],
                        contentDescription = "Toggle password visibility",
                        onClick = { isPasswordVisible = !isPasswordVisible }
                    ),
                    visualTransformation =
                        if (isPasswordVisible) VisualTransformation.None
                        else PasswordVisualTransformation('•'),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))

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
                onClick = { onClickSignIn() }
            ) {
                Icon(
                    imageVector = Icons.Sharp.Login,
                    tint = MaterialTheme.colors.background,
                    contentDescription = "Sign in"
                )
            }
        }
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    GameOfLifeTheme {
        Surface(
            Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val emailOrLogin by remember { mutableStateOf("") }
            val password by remember { mutableStateOf("") }

            LoginScreen(emailOrLogin, password)
        }
    }
}
