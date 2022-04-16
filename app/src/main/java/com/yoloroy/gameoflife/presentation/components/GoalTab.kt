package com.yoloroy.gameoflife.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun GoalTab(
    selected: Boolean,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit = {}
) {
    LeadingIconTab(
        selected = selected,
        onClick = onClick,
        text = { Text(text = text) },
        icon = { Icon(imageVector = icon, contentDescription = "$text tab") },
        selectedContentColor = MaterialTheme.colors.secondary,
        unselectedContentColor = MaterialTheme.colors.secondaryVariant
    )
}
