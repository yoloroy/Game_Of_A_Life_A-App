package com.yoloroy.gameoflife.presentation.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ExpandLess
import androidx.compose.material.icons.sharp.ExpandMore
import androidx.compose.material.icons.sharp.Visibility
import androidx.compose.material.icons.sharp.VisibilityOff
import androidx.compose.ui.graphics.vector.ImageVector

val Icons.Sharp.VisibilityToggle get() = ToggleIcon(Visibility, VisibilityOff)
val Icons.Sharp.ExpandToggle get() = ToggleIcon(ExpandLess, ExpandMore)

class ToggleIcon(
    private val onTrue: ImageVector,
    private val onFalse: ImageVector
) {
    operator fun get(toggleValue: Boolean) = if (toggleValue) onTrue else onFalse
}
