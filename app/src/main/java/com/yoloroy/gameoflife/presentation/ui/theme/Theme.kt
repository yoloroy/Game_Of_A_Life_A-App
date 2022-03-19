package com.yoloroy.gameoflife.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val ColorPalette = darkColors(
    primary = YellowAccent,
    secondary = BlueSecondary,
    secondaryVariant = BlueSecondaryVariant,
    background = BlueBackground,
    surface = BlueSurface,
    error = RedError
)

@Composable
fun GameOfLifeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}