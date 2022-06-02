package com.yoloroy.gameoflife.presentation.dreams_library

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Tune
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.presentation.components.GoalTag
import com.yoloroy.gameoflife.presentation.ui.icons.ToggleIcon
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.util.Screen

@ExperimentalMaterialApi
@Composable
fun DreamsLibraryScreen(navController: NavController, viewModel: DreamsLibraryViewModel = hiltViewModel()) {
    with(viewModel) {
        val libraryDreamsUi by remember(dreams) {
            derivedStateOf { LibraryDreamsUi.fromResource(dreams) }
        }

        BackHandler(true, navController::popBackStack)
        DreamsLibraryScreen(
            tags = tags.toList(),
            libraryDreamsUi = libraryDreamsUi,
            onAddTag = ::addTag,
            onRemoveTag = ::removeTag,
            onClickBack = navController::popBackStack,
            onClickTagInDream = ::addTag,
            onClickDream = { navController.navigate("${Screen.DreamsDetailsScreen.route}/$it") }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun DreamsLibraryScreen(
    tags: List<String>,
    libraryDreamsUi: LibraryDreamsUi,
    onAddTag: (String) -> Unit = {},
    onRemoveTag: (String) -> Unit = {},
    onClickBack: () -> Unit = {},
    onClickTagInDream: (String) -> Unit = {},
    onClickDream: (id: String) -> Unit = {}
) {
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    var isConcealed by remember { mutableStateOf(backdropState.isConcealed) }

    LaunchedEffect(isConcealed) {
        if (isConcealed) {
            backdropState.reveal()
        } else {
            backdropState.conceal()
        }
    }

    BackdropScaffold(
        scaffoldState = backdropState,
        appBar = { ActionBar(
            onClickBack = onClickBack,
            onClickOptions = { isConcealed = !isConcealed },
            isRevealed = backdropState.isConcealed
        ) },
        backLayerContent = { SearchTuningLayer(tags, onAddTag, onRemoveTag) },
        frontLayerContent = { libraryDreamsUi.Content(onClickTagInDream, onClickDream) },
        backLayerBackgroundColor = MaterialTheme.colors.background,
        frontLayerShape = CutCornerShape(topStart = 12.dp)
    )
}

@Composable
private fun ActionBar(onClickBack: () -> Unit, onClickOptions: () -> Unit, isRevealed: Boolean) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        title = {
            Text(
                text = "Dreams library",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        },
        actions = actions@{
            IconButton(onClick = onClickOptions) {
                Icon(
                    imageVector = Icons.Sharp.TuneOrClose[isRevealed],
                    contentDescription = "Tune search",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp
    )
}

@Composable
private fun SearchTuningLayer(
    tags: List<String>,
    onAddTag: (String) -> Unit,
    onRemoveTag: (String) -> Unit
) {
    var searchFieldValue by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TagField(
            value = searchFieldValue,
            onValueChange = { searchFieldValue = it },
            onAddTag = {
                onAddTag(it)
                searchFieldValue = ""
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow {
            for (tag in tags) {
                Row {
                    GoalTag(text = tag, onClick = {})
                    IconButton(onClick = { onRemoveTag(tag) }) {
                        Icon(
                            imageVector = Icons.Sharp.Close,
                            contentDescription = "Remove tag $tag"
                        )
                    }
                }
            }
        }
    }
}

private val Icons.Sharp.TuneOrClose get() = ToggleIcon(Tune, Close)

@SuppressLint("UnrememberedMutableState", "FlowOperatorInvokedInComposition")
@ExperimentalMaterialApi
@Preview
@Composable
fun DreamsLibraryScreenPreview() {
    val dreams = listOf(
        Dream(
            id = "-1",
            name = "Android in one hour",
            description = "Learning how to build hello world application",
            tags = listOf("IT", "Android", "Preview")
        ),
        Dream(
            id = "-1",
            name = "Android in two hours",
            description = "Learning how to build hello world application",
            tags = listOf("IT", "Android", "Preview")
        ),
        Dream(
            id = "-1",
            name = "Android in three hours",
            description = "Learning how to build hello world application",
            tags = listOf("IT", "Android", "Preview")
        )
    )
    val tags = listOf("Android", "IT", "Easy start")

    GameOfLifeTheme {
        DreamsLibraryScreen(tags, LibraryDreamsUi.DreamsContent(dreams))
    }
}
