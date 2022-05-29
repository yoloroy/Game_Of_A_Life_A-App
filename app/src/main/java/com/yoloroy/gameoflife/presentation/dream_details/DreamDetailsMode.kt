package com.yoloroy.gameoflife.presentation.dream_details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Remove
import androidx.compose.ui.graphics.vector.ImageVector

enum class DreamDetailsMode(val icon: ImageVector? = null) {
    Adding(Icons.Sharp.Add),
    Removing(Icons.Sharp.Remove),
    View
}