package com.yoloroy.gameoflife.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.ui.theme.text

@Composable
internal fun SkillItem(name: String, level: Int) {
    Surface(
        color = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.text,
        border = BorderStroke(1.dp, MaterialTheme.colors.secondaryVariant)
    ) {
        Text(
            text = "$name: $level",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun SkillItemPreview() {
    GameOfLifeTheme {
        Column(
            modifier = Modifier.background(MaterialTheme.colors.background)
        ) {
            SkillItem(name = "IOS", level = 15)
            Spacer(modifier = Modifier.height(8.dp))
            SkillItem(name = "Android", level = 12)
        }
    }
}
