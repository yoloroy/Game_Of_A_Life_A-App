package com.yoloroy.gameoflife.presentation.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yoloroy.gameoflife.presentation.dream_details.DreamDetailsScreenPreview
import com.yoloroy.gameoflife.presentation.dreams.DreamsPagePreview
import com.yoloroy.gameoflife.presentation.dreams_library.DreamsLibraryScreenPreview
import com.yoloroy.gameoflife.presentation.entering.login.LoginPreview
import com.yoloroy.gameoflife.presentation.entering.registration.RegistrationPreview
import com.yoloroy.gameoflife.presentation.profile.ProfileScreenPreview
import com.yoloroy.gameoflife.presentation.settings.list.SettingsListScreenPreview
import com.yoloroy.gameoflife.presentation.settings.profile.ProfileSettingsPreview

@Preview
@Composable
fun Login() {
    LoginPreview()
}

@Preview
@Composable
fun Registration() {
    RegistrationPreview()
}

@Preview
@Composable
fun DreamsPage() {
    DreamsPagePreview()
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
    ProfileSettingsPreview()
}
