package com.yoloroy.gameoflife.presentation.settings.list

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
fun SignOutDialog(
    show: Boolean,
    onSignOut: () -> Unit = {},
    hide: () -> Unit = {}
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { hide() },
            text = { Text("You're sure, you want to Sign out?") },
            confirmButton = {
                TextButton(onClick = onSignOut) {
                    Text("Sign out")
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignOutPopupPreview() {
    GameOfLifeTheme {
        SignOutDialog(true)
    }
}
