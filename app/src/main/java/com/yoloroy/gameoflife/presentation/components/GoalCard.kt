package com.yoloroy.gameoflife.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
fun GoalCard(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    additionToTitle: String? = null,
    tags: List<String> = emptyList(),
    onClickTag: (String) -> Unit = {},
    tagView: @Composable (String, () -> Unit) -> Unit = { name, action -> GoalTag(name, action) },
    actions: List<Pair<String, () -> Unit>> = emptyList(),
    onClick: () -> Unit = {}
) {
    val tagsListState = rememberLazyListState()

    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(MaterialTheme.colors.background)
            .border(1.dp, MaterialTheme.colors.secondaryVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle1
            )
            if (additionToTitle != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = additionToTitle,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.caption
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = content,
                color = MaterialTheme.colors.onBackground
            )
        }
        LazyRow(
            state = tagsListState,
            modifier = Modifier
        ) {
            items (tags) { tag ->
                tagView(tag) { onClickTag(tag) }
            }
        }
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            for ((text, action) in actions) {
                Action(text, action)
            }
        }
    }
}

@Composable
private fun Action(text: String, action: () -> Unit) {
    TextButton(onClick = action) {
        Text(
            text = text,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.button
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E2433, showSystemUi = true)
@Composable
fun StandardCardPreview() {
    GameOfLifeTheme {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            GoalCard(
                title = "Title",
                additionToTitle = "*it is preview*",
                content = "T tex ttextte xtete x te xtex etetxtex etx etxete",
                tags = listOf("tag1", "o_a_o_a_o_a_o_a", "bigibigtasadsadasdg"),
                actions = listOf("Action" to {})
            )
        }
    }
}
