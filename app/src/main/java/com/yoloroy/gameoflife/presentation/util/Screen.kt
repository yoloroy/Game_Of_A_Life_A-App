package com.yoloroy.gameoflife.presentation.util

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login")
    object RegistrationScreen : Screen("registration")
    object DreamsScreen : Screen("dreams")
    object DreamsLibraryScreen : Screen("dreams library") {
        fun route(vararg tags: String) = "$route?tags=${tags.joinToString(",")}"
    }
    object DreamsDetailsScreen : Screen("dream details")
    object ProfileScreen : Screen("profile")
    object SettingsListScreen : Screen("settings list")
    object ProfileSettingsScreen : Screen("settings profile")
}
