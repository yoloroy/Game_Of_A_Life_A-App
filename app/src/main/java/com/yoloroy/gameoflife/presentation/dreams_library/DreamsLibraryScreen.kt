package com.yoloroy.gameoflife.presentation.dreams_library

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Tune
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.Dream
import com.yoloroy.gameoflife.presentation.components.GoalCard
import com.yoloroy.gameoflife.presentation.components.GoalTag
import com.yoloroy.gameoflife.presentation.ui.icons.ToggleIcon
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.util.Screen

@ExperimentalMaterialApi
@Composable
fun DreamsLibraryScreen(navController: NavController, viewModel: DreamsLibraryViewModel = hiltViewModel()) {
    with(viewModel) {
        val tags by tags.observeAsState()
        val dreams by dreams.observeAsState()

        BackHandler(true, navController::popBackStack)
        DreamsLibraryScreen(
            tags = tags!!.toList(),
            dreams = dreams!!,
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
    dreams: Resource<List<Dream>>,
    onAddTag: (String) -> Unit = {},
    onRemoveTag: (String) -> Unit = {},
    onClickBack: () -> Unit = {},
    onClickTagInDream: (String) -> Unit = {},
    onClickDream: (id: String) -> Unit = {}
) {
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    var isConcealed by remember { mutableStateOf(backdropState.isConcealed) }

    LaunchedEffect(backdropState) {
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
        frontLayerContent = { DreamsLayer(dreams, onClickTagInDream, onClickDream) },
        backLayerBackgroundColor = MaterialTheme.colors.background,
        frontLayerShape = CutCornerShape(topStart = 12.dp)
    )
}

@Composable
private fun DreamsLayer(
    dreams: Resource<List<Dream>>,
    onClickTag: (String) -> Unit,
    onClickDream: (id: String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        when (dreams) {
            is Resource.Success -> dreamsSuccessResult(dreams.data!!, onClickTag, onClickDream)
            is Resource.Loading -> dreamsLoadingResult()
            is Resource.Error -> dreamsErrorResult()
        }
    }
}

private fun LazyListScope.dreamsSuccessResult(
    dreams: List<Dream>,
    onClickTag: (String) -> Unit,
    onClickDream: (id: String) -> Unit
) {
    item {
        Text(
            text = "See ${dreams.size} results",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.subtitle2
        )
    }
    items(dreams) { dream ->
        GoalCard(
            title = dream.name,
            content = dream.description,
            tags = dream.tags,
            modifier = Modifier.fillMaxWidth(),
            onClickTag = onClickTag,
            onClick = { onClickDream(dream.id) }
        )
    }
}

private fun LazyListScope.dreamsLoadingResult() {
    item {
        Text(text = "Loading results..")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

private fun LazyListScope.dreamsErrorResult() {
    item {
        Text(text = ":(") // TODO
    }
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
        DreamsLibraryScreen(tags, Resource.Success(dreams))
    }
}
