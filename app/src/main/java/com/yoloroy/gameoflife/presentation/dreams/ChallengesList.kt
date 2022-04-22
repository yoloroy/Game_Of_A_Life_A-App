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
import com.yoloroy.gameoflife.presentation.components.GoalCard
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
internal fun ChallengesList(
    challenges: List<ChallengeCardData>,
    onClickComplete: (id: String) -> Unit = {},
    onClickChallenge: (id: String) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            start = 12.dp,
            end = 12.dp,
            top = 8.dp,
            bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(challenges) { challenge ->
            GoalCard(
                title = challenge.name,
                additionToTitle = "${challenge.dreamName} #${challenge.number}",
                content = challenge.description,
                modifier = Modifier.fillMaxWidth(),
                actions = listOf("Complete" to { onClickComplete(challenge.id) }),
                onClick = { onClickChallenge(challenge.id) }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E2433, showSystemUi = true)
@Composable
fun ChallengesListPreview() {
    GameOfLifeTheme {
        ChallengesList(
            challenges = List(3) { i ->
                ChallengeCardData(
                    dreamName = "Goal tutorial",
                    id = "-1",
                    number = i + 1,
                    name = "name",
                    description = "description"
                )
            }
        )
    }
}
