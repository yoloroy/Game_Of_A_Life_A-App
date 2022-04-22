package com.yoloroy.gameoflife.presentation.dreams

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.domain.model.Dream
import com.yoloroy.gameoflife.presentation.components.GoalCard
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
internal fun DreamsList(
    dreams: List<Dream>,
    onClickAddDream: () -> Unit = {},
    onClickTag: (String) -> Unit = {},
    onClickDream: (id: String) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dreams) { dream ->
            GoalCard(
                title = dream.name,
                content = dream.description,
                tags = dream.tags,
                modifier = Modifier.fillMaxWidth(),
                onClickTag = onClickTag,
                actions = listOf("See more" to { onClickDream(dream.id) }),
                onClick = { onClickDream(dream.id) }
            )
        }
        if (dreams.isEmpty()) {
            item {
                DreamAdding(onClickAddDream)
            }
        }
    }
}

@Composable
fun DreamAdding(onClickAdd: () -> Unit) {
    GoalCard(
        title = "There are no more dreams to make them come true?",
        content = "You always can find more of them in our library!",
        actions = listOf("Find your dream" to onClickAdd),
        tags = listOf("Goal team"),
        onClick = onClickAdd
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF0E2433, showSystemUi = true)
@Composable
fun DreamsListPreview() {
    GameOfLifeTheme {
        DreamsList(
            dreams = listOf(
                Dream(
                    "-1",
                    "GOAL tutorial",
                    "Learning how to use GOAL app",
                    listOf("Goal team", "Getting started")
                )
            ),
            onClickAddDream = {},
            onClickTag = {},
            onClickDream = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E2433, showSystemUi = true)
@Composable
fun DreamsListEmptyPreview() {
    GameOfLifeTheme {
        DreamsList(
            dreams = emptyList(),
            onClickAddDream = {},
            onClickTag = {},
            onClickDream = {}
        )
    }
}
