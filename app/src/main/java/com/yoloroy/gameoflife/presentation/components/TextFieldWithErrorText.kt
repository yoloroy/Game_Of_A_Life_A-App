package com.yoloroy.gameoflife.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme

@Composable
fun TextFieldWithErrorText(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    isError: Boolean = false,
    trailingIcon: TrailingIcon? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            modifier = modifier,
            trailingIcon = trailingIcon?.run {{
                Icon(
                    imageVector = image,
                    contentDescription = contentDescription,
                    modifier = Modifier.clickable(onClick = onClick)
                )
            }},
            isError = isError,
            visualTransformation = visualTransformation
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = if (isError) errorMessage else "",
            color = MaterialTheme.colors.error,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
    }
}

data class TrailingIcon(
    val image: ImageVector,
    val contentDescription: String? = null,
    val onClick: () -> Unit = {}
)

@Preview
@Composable
fun TextFieldWithErrorTextPreview() {
    GameOfLifeTheme {
        var text by remember { mutableStateOf("text") }

        Surface(color = MaterialTheme.colors.background) {
            Column {
                TextFieldWithErrorText(
                    value = text,
                    onValueChange = { text = it },
                    label = "Text Hint",
                    modifier = Modifier.fillMaxWidth(),
                    errorMessage = "Error",
                    isError = false,
                    trailingIcon = TrailingIcon(Icons.Sharp.Visibility),
                    visualTransformation = PasswordVisualTransformation('•')
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextFieldWithErrorText(
                    value = "",
                    onValueChange = { text = it },
                    label = "Text hint",
                    modifier = Modifier.fillMaxWidth(),
                    errorMessage = "Error",
                    isError = true
                )
            }
        }
    }
}
