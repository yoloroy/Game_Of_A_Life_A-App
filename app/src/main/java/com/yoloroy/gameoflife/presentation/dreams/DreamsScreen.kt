package com.yoloroy.gameoflife.presentation.dreams

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Checklist
import androidx.compose.material.icons.sharp.TaskAlt
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yoloroy.gameoflife.presentation.components.GoalTab
import com.yoloroy.gameoflife.presentation.dreams.DreamsPageTab.Challenges
import com.yoloroy.gameoflife.presentation.dreams.DreamsPageTab.Dreams
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.ui.theme.pressStartFonts

@Composable
fun DreamsScreen(
    onClickAddDream: () -> Unit = {},
    onClickTag: (id: String) -> Unit = {},
    onClickDream: (id: String) -> Unit = {}
) {
    var tab by remember { mutableStateOf(Dreams) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(48.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dreams",
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f),
                color = MaterialTheme.colors.primary,
                fontFamily = pressStartFonts,
                fontSize = 18.sp
            )
            IconButton(onClick = onClickAddDream) {
                Icon(
                    imageVector = Icons.Sharp.Add,
                    contentDescription = "Add dream",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
        TabRow(
            selectedTabIndex = tab.index,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.secondary
        ) {
            GoalTab(
                tab == Dreams,
                onClick = { tab = Dreams },
                text = "Dreams",
                icon = Icons.Sharp.TaskAlt
            )
            GoalTab(
                tab == Challenges,
                onClick = { tab = Challenges },
                text = "Challenges",
                icon = Icons.Sharp.Checklist
            )
        }
        // TODO pager
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E2433)
@Composable
fun DreamsScreenPreview() {
    GameOfLifeTheme {
        DreamsScreen()
    }
}

private enum class DreamsPageTab(
    val index: Int
) {
    Dreams(0),
    Challenges(1)
}
