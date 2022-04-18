package com.yoloroy.gameoflife.presentation.settings.list

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
fun ResetStatsDialog(
    show: Boolean,
    onResetStats: () -> Unit = {},
    hide: () -> Unit = {}
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { hide() },
            title = { Text("You’re sure, you want to reset all your stats?") },
            text = { Text("You will have the option to cancel resetting of all data within two hours.") },
            confirmButton = {
                TextButton(onClick = onResetStats) {
                    Text("Reset stats")
                }
            },
            dismissButton = {
                TextButton(onClick = hide) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
fun ResetStatsPopupPreview() {
    GameOfLifeTheme {
        ResetStatsDialog(true)
    }
}
