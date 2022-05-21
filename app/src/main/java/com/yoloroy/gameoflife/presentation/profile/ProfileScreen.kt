package com.yoloroy.gameoflife.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.History
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.skydoves.landscapist.glide.GlideImage
import com.yoloroy.gameoflife.R
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.model.data.Skill
import com.yoloroy.gameoflife.presentation.components.BottomNavigationScreen
import com.yoloroy.gameoflife.presentation.components.WithMainNavigationBar
import com.yoloroy.gameoflife.presentation.ui.icons.ExpandToggle
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.ui.theme.text
import com.yoloroy.gameoflife.presentation.util.Screen

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {
    WithMainNavigationBar(
        currentScreen = BottomNavigationScreen(Screen.ProfileScreen),
        navController = navController
    ) {
        when (viewModel.profile) {
            is Resource.Success -> ProfileScreen(
                profile = viewModel.profile.data!!,
                modifier = Modifier.weight(1f),
                onClickSettings = { navController.navigate(Screen.SettingsListScreen.route) },
                onClickDream = { (id) -> navController.navigate(Screen.DreamsDetailsScreen.route + "/$id") }
            )
            is Resource.Error -> ErrorContent(error = viewModel.profile.error!!)
            is Resource.Loading -> LoadingContent()
        }
    }
}

@Composable
fun ProfileScreen(
    profile: Profile,
    modifier: Modifier = Modifier,
    onClickSettings: () -> Unit = {},
    onClickDream: (Dream) -> Unit = {}
) {
    Column(modifier = modifier.padding(16.dp)) {
        ToolBar(onClickSettings)
        MainInfo(profile.imageUrl, profile.name, profile.level, profile.exp, profile.maxExp)
        Spacer(modifier = Modifier.height(24.dp))
        SkillsList(profile.skills)
        Spacer(modifier = Modifier.height(24.dp))
        FulfilledDreams(profile.fulfilledDreams, onClickDream)
    }
}

@Composable
private fun ToolBar(onClickSettings: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = onClickSettings
        ) {
            Icon(
                imageVector = Icons.Sharp.Settings,
                contentDescription = "Settings",
                tint = MaterialTheme.colors.text
            )
        }
    }
}

@Composable
private fun MainInfo(imageUrl: String?, name: String, level: Int, exp: Int, maxExp: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(160.dp)
        ) {
            GlideImage(
                imageModel = imageUrl,
                placeHolder = Icons.Sharp.AccountCircle,
                previewPlaceholder = R.drawable.img_profile_placeholder,
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxSize()
            )
            CircularProgressIndicator(
                progress = exp.toFloat() / maxExp,
                modifier = Modifier
                    .fillMaxSize()
                    .scale(-1f),
                color = MaterialTheme.colors.secondary
            )
        }
        Text(
            text = name,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = "$level lvl, $exp / $maxExp exp",
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
private fun SkillsList(skills: List<Skill>) {
    FlowRow(
        mainAxisSpacing = 12.dp,
        crossAxisSpacing = 12.dp
    ) {
        for (skill in skills) {
            SkillItem(name = skill.name, level = skill.level)
        }
    }
}

@Composable
private fun FulfilledDreams(dreams: List<Dream>, onClickDream: (Dream) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colors.surface,
        border = BorderStroke(1.dp, MaterialTheme.colors.secondaryVariant)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Sharp.History,
                        contentDescription = "Fulfilled Dreams ic"
                    )
                }
                Text(
                    text = "FulfilledDreams",
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        imageVector = Icons.Sharp.ExpandToggle[isExpanded],
                        contentDescription = "Expand list button"
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.padding(12.dp)
            ) {
                items(dreams) { dream ->
                    DreamItem(
                        id = dream.id,
                        name = dream.name,
                        onClick = { onClickDream(dream) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ErrorContent(error: Throwable) {
    // TODO
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    GameOfLifeTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            ProfileScreen(
                profile = Profile(
                    name = "AndroidDancer",
                    level = 3,
                    exp = 83,
                    maxExp = 128,
                    skills = listOf(
                        Skill(name = "Android", level = 11),
                        Skill(name = "Planning", level = 8),
                        Skill(name = "Design", level = 6),
                        Skill(name = "Intelligence", level = 6),
                        Skill(name = "Endurance", level = 5)
                    ),
                    fulfilledDreams = listOf(
                        Dream("-1", "Your first fulfilled dream", "...", emptyList())
                    )
                ),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
