package com.yoloroy.gameoflife.presentation.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yoloroy.gameoflife.presentation.dream_details.DreamDetailsScreen
import com.yoloroy.gameoflife.presentation.dreams.DreamsScreen
import com.yoloroy.gameoflife.presentation.dreams_library.DreamsLibraryScreen
import com.yoloroy.gameoflife.presentation.entering.login.LoginScreen
import com.yoloroy.gameoflife.presentation.entering.registration.RegistrationScreen
import com.yoloroy.gameoflife.presentation.profile.ProfileScreen
import com.yoloroy.gameoflife.presentation.settings.list.SettingsListScreen
import com.yoloroy.gameoflife.presentation.settings.profile.ProfileSettingsScreen
import com.yoloroy.gameoflife.presentation.util.Screen

@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(Screen.RegistrationScreen.route) {
            RegistrationScreen(navController)
        }
        composable(Screen.DreamsScreen.route) {
            DreamsScreen(navController)
        }
        composable(
            "${Screen.DreamsLibraryScreen.route}?tags={tags}",
            arguments = listOf(
                navArgument("tags") {
                    type = NavType.StringArrayType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            DreamsLibraryScreen(navController)
        }
        composable(
            "${Screen.DreamsDetailsScreen.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            DreamDetailsScreen(navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(Screen.SettingsListScreen.route) {
            SettingsListScreen(navController)
        }
        composable(Screen.ProfileSettingsScreen.route) {
            ProfileSettingsScreen(navController)
        }
    }
}
