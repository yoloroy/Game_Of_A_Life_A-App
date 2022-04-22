package com.yoloroy.gameoflife.presentation.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yoloroy.gameoflife.presentation.dream_details.DreamDetailsScreenPreview
import com.yoloroy.gameoflife.presentation.dreams.DreamsScreenPreview
import com.yoloroy.gameoflife.presentation.dreams_library.DreamsLibraryScreenPreview
import com.yoloroy.gameoflife.presentation.entering.login.LoginScreenPreview
import com.yoloroy.gameoflife.presentation.entering.registration.RegistrationScreenPreview
import com.yoloroy.gameoflife.presentation.profile.ProfileScreenPreview
import com.yoloroy.gameoflife.presentation.settings.list.SettingsListScreenPreview
import com.yoloroy.gameoflife.presentation.settings.profile.ProfileSettingsScreenPreview

@Preview
@Composable
fun Login() {
    LoginScreenPreview()
}

@Preview
@Composable
fun Registration() {
    RegistrationScreenPreview()
}

@Preview
@Composable
fun DreamsScreen() {
    DreamsScreenPreview()
}

@ExperimentalMaterialApi
@Preview
@Composable
fun DreamsLibrary() {
    DreamsLibraryScreenPreview()
}

@Preview
@Composable
fun DreamDetails() {
    DreamDetailsScreenPreview()
}

@Preview
@Composable
fun Profile() {
    ProfileScreenPreview()
}

@ExperimentalMaterialApi
@Preview
@Composable
fun SettingsList() {
    SettingsListScreenPreview()
}

@Preview
@Composable
fun ProfileSettings() {
    ProfileSettingsScreenPreview()
}
