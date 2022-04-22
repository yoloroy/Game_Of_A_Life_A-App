package com.yoloroy.gameoflife.presentation.dreams_library

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Cancel
import androidx.compose.material.icons.sharp.Tag
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.ui.theme.disabled
import com.yoloroy.gameoflife.presentation.ui.theme.text

@Composable
internal fun TagField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onAddTag: (String) -> Unit = {}
) {
    val placeholderText = remember { listOf("cooking", "programming", "math", "sport", "self esteem").random() } // TODO refactor

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = CutCornerShape(topStart = 8.dp, topEnd = 8.dp)
            )
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text(text = placeholderText) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Sharp.Tag,
                    contentDescription = "Search icon",
                    tint = MaterialTheme.colors.disabled
                )
            },
            trailingIcon =
                if (value.isEmpty()) { null }
                else ({
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(imageVector = Icons.Sharp.Cancel, contentDescription = "Clear field")
                    }
                }),
            colors = textFieldColors(
                textColor = MaterialTheme.colors.text,
                backgroundColor = MaterialTheme.colors.background
                /*backgroundColor = MaterialTheme.colors.secondaryVariant*/
            )
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                /*.background(
                    color = MaterialTheme.colors.secondary,
                    shape = CutCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                )*/
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = CutCornerShape(topEnd = 8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {
                    val tag = value.takeIf { it.isNotEmpty() } ?: placeholderText
                    onAddTag(tag)
                }
            ) {
                Icon(
                    imageVector = Icons.Sharp.Add,
                    contentDescription = "Add Tag",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(backgroundColor = 0xFF0E2433, showBackground = true)
@Composable
fun TagFieldPreview() {
    val textState = mutableStateOf("asdasd")

    GameOfLifeTheme {
        var text by remember { textState }

        TagField(
            value = text,
            modifier = Modifier.padding(20.dp),
            onValueChange = { text = it },
            onAddTag = {}
        )
    }
}
