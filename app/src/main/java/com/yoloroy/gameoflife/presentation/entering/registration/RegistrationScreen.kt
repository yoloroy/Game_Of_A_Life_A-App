package com.yoloroy.gameoflife.presentation.entering.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.PersonAddAlt
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
fun RegistrationScreen(navController: NavController, viewModel: RegistrationViewModel = hiltViewModel()) {
    with(viewModel) {
        val email by viewModel.email.observeAsState()
        val login by viewModel.login.observeAsState()
        val password by viewModel.password.observeAsState()

        RegistrationScreen(
            email = email!!,
            login = login!!,
            password = password!!,
            onEmailUpdate = ::onEmailUpdate,
            onLoginUpdate = ::onLoginUpdate,
            onPasswordUpdate = ::onPasswordUpdate,
            onRegister = ::onRegister,
            moveToLogin = { navController.navigate(Screen.LoginScreen.route) }
        )
    }
}

@Composable
fun RegistrationScreen(
    email: String,
    login: String,
    password: String,
    onEmailUpdate: (String) -> Unit = {},
    onLoginUpdate: (String) -> Unit = {},
    onPasswordUpdate: (String) -> Unit = {},
    onRegister: (email: String, login: String, password: String) -> Unit = { _, _, _ ->},
    moveToLogin: () -> Unit = {}
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
                        text = "Registration",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                // email
                TextFieldWithErrorText(
                    value = email,
                    onValueChange = onEmailUpdate,
                    label = "Email",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))

                // login
                TextFieldWithErrorText(
                    value = login,
                    onValueChange = onLoginUpdate,
                    label = "Login",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))

                // password
                TextFieldWithErrorText(
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

                // to sign in
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(
                            style = MaterialTheme.typography.body1.toSpanStyle()
                                    + SpanStyle(color = MaterialTheme.colors.onBackground)
                        ) {
                            append("Already have an account? ")
                        }
                        withStyle(
                            style = MaterialTheme.typography.body1.toSpanStyle()
                                    + SpanStyle(color = MaterialTheme.colors.secondary)
                        ) {
                            append("Sign in!")
                        }
                    },
                    onClick = { moveToLogin() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onRegister(email, login, password) }
            ) {
                Icon(
                    imageVector = Icons.Sharp.PersonAddAlt,
                    tint = MaterialTheme.colors.background,
                    contentDescription = "Register"
                )
            }
        }
    )
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    GameOfLifeTheme {
        Surface(
            Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            RegistrationScreen("em@i.l", "", "")
        }
    }
}
