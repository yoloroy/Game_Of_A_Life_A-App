package com.yoloroy.gameoflife.presentation.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
internal fun DreamItem(id: String, name: String, onClick: (id: String) -> Unit) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Row {
            Text(
                text = name,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .align(CenterVertically),
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle2
            )
            IconButton(onClick = { onClick(id) }) {
                Icon(
                    imageVector = Icons.Sharp.NavigateNext,
                    contentDescription = "Go to Dream details"
                )
            }
        }
    }
}

@Preview
@Composable
fun DreamItemPreview() {
    GameOfLifeTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            DreamItem("-1", "Dream dream dr.Eam dReAm DrEaM drEAm DReaM DREAm dr.E.Am") {}
        }
    }
}
