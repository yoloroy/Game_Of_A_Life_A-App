package com.yoloroy.gameoflife.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yoloroy.gameoflife.presentation.ui.icons.DreamsIcon
import com.yoloroy.gameoflife.presentation.ui.icons.ProfileIcon
import com.yoloroy.gameoflife.presentation.ui.icons.ToggleIcon
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.util.Screen

@Composable
fun MainNavigationBar(
    currentScreen: BottomNavigationScreen,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colors.secondaryVariant)
        )
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background
        ) {
            BottomNavigationScreen.list.forEach { screen ->
                val selected = screen == currentScreen

                BottomNavigationItem(
                    selected = selected,
                    onClick = { navController.navigate(screen.route) },
                    icon = {
                        Icon(
                            imageVector = screen.icon[selected],
                            contentDescription = "Navigate to ${screen.name}"
                        )
                    },
                    label = {
                        Text(text = screen.name)
                    },
                    selectedContentColor = MaterialTheme.colors.secondary,
                    unselectedContentColor = MaterialTheme.colors.secondaryVariant
                )
            }
        }
    }
}

@Preview
@Composable
fun MainNavigationBarPreview() {
    GameOfLifeTheme {
        MainNavigationBar(BottomNavigationScreen.Dreams, rememberNavController())
    }
}

@Composable
fun WithMainNavigationBar(
    currentScreen: BottomNavigationScreen,
    navController: NavController,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        content()
        MainNavigationBar(
            currentScreen = currentScreen,
            navController = navController
        )
    }
}

sealed class BottomNavigationScreen(
    val name: String,
    val route: String,
    val icon: ToggleIcon
) {
    object Dreams : BottomNavigationScreen("Dreams", Screen.DreamsScreen.route, DreamsIcon)

    object Profile : BottomNavigationScreen("Profile", Screen.ProfileScreen.route, ProfileIcon)

    companion object {
        val list get() = listOf(Dreams, Profile)

        operator fun invoke(screen: Screen) = when (screen) {
            is Screen.DreamsScreen -> Dreams
            is Screen.ProfileScreen -> Profile
            else -> throw NotImplementedError()
        }
    }
}
