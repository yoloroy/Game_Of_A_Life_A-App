package com.yoloroy.gameoflife.presentation.settings.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yoloroy.gameoflife.presentation.components.PasswordTextField
import com.yoloroy.gameoflife.presentation.components.TextFieldWithErrorText
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.util.Screen

@Composable
fun ProfileSettingsScreen(navController: NavController, viewModel: ProfileSettingsViewModel = hiltViewModel()) {
    ProfileSettingsScreen(
        username = viewModel.username,
        setUserName = viewModel::username::set,
        email = viewModel.email,
        setEmail = viewModel::email::set,
        previousPassword = viewModel.previousPassword,
        setPreviousPassword = viewModel::previousPassword::set,
        password = viewModel.password,
        setPassword = viewModel::password::set,
        confirmPassword = viewModel.confirmPassword,
        setConfirmPassword = viewModel::confirmPassword::set,
        onClickBack = navController::popBackStack,
        confirm = {
            viewModel.confirmChanges {
                navController.navigate(Screen.ProfileScreen.route)
            }
        }
    )
}

@Composable
fun ProfileSettingsScreen(
    username: String,
    setUserName: (String) -> Unit,
    email: String,
    setEmail: (String) -> Unit,
    previousPassword: String,
    setPreviousPassword: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    confirmPassword: String,
    setConfirmPassword: (String) -> Unit,
    onClickBack: () -> Unit = {},
    confirm: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar(onClickBack = onClickBack)
        },
        content = {
            Content(
                username, setUserName,
                email, setEmail,
                previousPassword, setPreviousPassword,
                password, setPassword,
                confirmPassword, setConfirmPassword
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = confirm) {
                Icon(
                    imageVector = Icons.Sharp.Done,
                    contentDescription = "Confirm"
                )
            }
        }
    )
}

@Composable
private fun TopBar(onClickBack: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        title = {
            Text(text = "Settings", color = MaterialTheme.colors.primary)
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp
    )
}

@Composable
private fun Content( // TODO error
    username: String,
    setUserName: (String) -> Unit,
    email: String,
    setEmail: (String) -> Unit,
    previousPassword: String,
    setPreviousPassword: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    confirmPassword: String,
    setConfirmPassword: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TextFieldWithErrorText(
            value = username,
            onValueChange = setUserName,
            label = "Username",
            modifier = Modifier.fillMaxWidth()
        )
        TextFieldWithErrorText(
            value = email,
            onValueChange = setEmail,
            label = "Email",
            modifier = Modifier.fillMaxWidth()
        )
        PasswordTextField(
            value = previousPassword,
            onValueChange = setPreviousPassword,
            label = "Previous password",
            modifier = Modifier.fillMaxWidth()
        )
        PasswordTextField(
            value = password,
            onValueChange = setPassword,
            label = "New password",
            modifier = Modifier.fillMaxWidth()
        )
        PasswordTextField(
            value = confirmPassword,
            onValueChange = setConfirmPassword,
            label = "Confirm new password",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ProfileSettingsScreenPreview() {
    GameOfLifeTheme {
        var username by remember { mutableStateOf("AndroidDancer") }
        var email by remember { mutableStateOf("em@i.l") }
        var previousPassword by remember { mutableStateOf("12345") }
        var password by remember { mutableStateOf("321") }
        var confirmPassword by remember { mutableStateOf("432") }

        ProfileSettingsScreen(
            username, { username = it },
            email, { email = it },
            previousPassword, { previousPassword = it },
            password, { password = it },
            confirmPassword, { confirmPassword = it }
        )
    }
}
