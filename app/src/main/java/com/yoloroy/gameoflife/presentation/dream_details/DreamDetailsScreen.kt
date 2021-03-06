package com.yoloroy.gameoflife.presentation.dream_details

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Challenge
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.model.data.Skill
import com.yoloroy.gameoflife.presentation.components.GoalCard
import com.yoloroy.gameoflife.presentation.components.GoalTag
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.util.Screen

@Composable
fun DreamDetailsScreen(navController: NavController, viewModel: DreamDetailsViewModel = hiltViewModel()) {
    with(viewModel) {
        DreamDetailsScreen(
            dream = dream,
            mode = mode,
            onClickBack = { navController.popBackStack() },
            onClickAdd = ::addDream,
            onClickDelete = ::removeDream,
            onClickTag = { tag ->
                val tagsArg = Uri.encode(tag)
                navController.navigate(Screen.DreamsLibraryScreen.route + "?tags=$tagsArg") }
        )
    }
}

@Composable
fun DreamDetailsScreen(
    dream: Resource<DreamDetail>,
    mode: DreamDetailsMode,
    onClickBack: () -> Unit = {},
    onClickAdd: () -> Unit = {},
    onClickDelete: () -> Unit = {},
    onClickTag: (String) -> Unit = {}
) {
    val context = LocalContext.current

    LaunchedEffect(dream is Resource.Error) {
        if (dream !is Resource.Error) return@LaunchedEffect

        Toast.makeText(
            context,
            dream.error.toString(), // TODO message with localization
            Toast.LENGTH_LONG
        ).show()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = CutCornerShape(topStart = 8.dp, topEnd = 8.dp),
            color = MaterialTheme.colors.background,
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.secondaryVariant)
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    ActionBar(dream.data?.name ?: "", mode, onClickBack, onClickAdd, onClickDelete)
                },
                content = {
                    if (dream is Resource.Loading) {
                        LoadingCircle()
                    }
                    dream.data?.let { data ->
                        DreamContent(data, onClickTag)
                    }
                }
            )
        }
    }
}

@Composable
private fun DreamContent(dream: DreamDetail, onClickTag: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(text = dream.description)
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Tags", style = MaterialTheme.typography.subtitle1)
        }
        item {
            LazyRow {
                items(dream.tags) { tag ->
                    GoalTag(text = tag) {
                        onClickTag(tag)
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Challenges", style = MaterialTheme.typography.subtitle1)
        }
        items(dream.challenges) { challenge ->
            GoalCard(
                title = challenge.name,
                content = challenge.description,
                tags = challenge.skills.map { "${it.name}: +${ it.level }" },
                tagView = { text, _ -> SkillItem(text) }
            )
        }
    }
}

@Composable
private fun LoadingCircle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ActionBar(
    dreamName: String,
    mode: DreamDetailsMode,
    onClickBack: () -> Unit,
    onClickAdd: () -> Unit,
    onCluckDelete: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        title = {
            Text(
                text = dreamName,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentHeight()
            )
        },
        actions = actions@{
            IconButton(
                onClick = when (mode) {
                    DreamDetailsMode.Adding -> onClickAdd
                    DreamDetailsMode.Removing -> onCluckDelete
                    else -> return@actions
                }
            ) {
                Icon(
                    imageVector = mode.icon!!,
                    contentDescription = "Action ${mode.name}",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun DreamDetailsScreenPreview() {
    GameOfLifeTheme {
        var mode by remember { mutableStateOf(DreamDetailsMode.Adding) }

        DreamDetailsScreen(
            dream = Resource.Success(
                DreamDetail(
                    "-1",
                    "Android in one hour",
                    "Learn how to build hello word application",
                    listOf("IT", "Android", "Easy start"),
                    listOf(
                        Challenge(
                            "-1",
                            "Download things",
                            "*Instructions on what and how to download*",
                            1
                        ),
                        Challenge(
                            "-1",
                            "Install things",
                            "*Instructions on what and how to install*",
                            2
                        ),
                        Challenge(
                            "-1",
                            "Create new project",
                            "*Instructions on how to create new project*",
                            3
                        ),
                        Challenge(
                            "-1",
                            "Wait for first build",
                            "Just wait",
                            4,
                            listOf(Skill("Endurance", 1))
                        )
                    )
                )
            ),
            mode = mode,
            onClickBack = {},
            onClickAdd = { mode = DreamDetailsMode.Removing },
            onClickDelete = { mode = DreamDetailsMode.Adding }
        )
    }
}
