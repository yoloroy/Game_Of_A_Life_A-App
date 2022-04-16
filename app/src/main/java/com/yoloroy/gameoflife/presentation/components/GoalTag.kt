package com.yoloroy.gameoflife.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
fun GoalTag(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick
    ) {
        Text(
            text = "#$text",
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.button
        )
    }
}

@Preview
@Composable
fun TagPreview() {
    GameOfLifeTheme {
        GoalTag("Tag") {}
    }
}
