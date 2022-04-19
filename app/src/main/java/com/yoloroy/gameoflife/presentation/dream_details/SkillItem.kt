package com.yoloroy.gameoflife.presentation.dream_details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
internal fun SkillItem(text: String) {
    Surface(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        )
    }
}

@Preview
@Composable
fun SkillItemPreview() {
    GameOfLifeTheme {
        SkillItem("Skill: +2")
    }
}
