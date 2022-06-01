@file:Suppress("unused")

package com.yoloroy.gameoflife.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val ColorPalette = darkColors(
    primary = YellowAccent,
    primaryVariant = YellowAccent,
    secondary = BlueSecondary,
    secondaryVariant = BlueSecondaryVariant,
    background = BlueBackground,
    surface = BlueSurface,
    error = RedError,
    onPrimary = BlueBackground,
    onSecondary = BlueBackground,
    onBackground = WhiteText,
    onSurface = WhiteText,
    onError = BlueBackground
)

val Colors.text get() = WhiteText
val Colors.warning get() = OrangeWarning
val Colors.disabled get() = GrayNotSelected
val Colors.link get() = Color(0xff30a0ff)

@Composable
fun GameOfLifeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes
    ) {
        rememberSystemUiController().apply {
            setStatusBarColor(color = MaterialTheme.colors.background)
        }

        content()
    }
}