package com.yoloroy.gameoflife.presentation.settings.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.NavigateNext
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.model.data.Skill
import com.yoloroy.gameoflife.presentation.components.ProfileImageWithExp
import com.yoloroy.gameoflife.presentation.ui.theme.GameOfLifeTheme
import com.yoloroy.gameoflife.presentation.ui.theme.disabled
import com.yoloroy.gameoflife.presentation.ui.theme.text
import com.yoloroy.gameoflife.presentation.ui.theme.warning
import com.yoloroy.gameoflife.presentation.util.Screen

@ExperimentalMaterialApi
@Composable
fun SettingsListScreen(navController: NavController, viewModel: SettingsListViewModel = hiltViewModel()) {
    SettingsListScreen(
        profile = viewModel.profile,
        onClickBack = navController::popBackStack,
        onClickProfileSettings = { navController.navigate(Screen.ProfileSettingsScreen.route) }
    ) {
        viewModel.resetStats {
            navController.navigate(Screen.ProfileScreen.route)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SettingsListScreen(
    profile: Resource<Profile>,
    onClickBack: () -> Unit = {},
    onClickProfileSettings: () -> Unit = {},
    onClickResetStats: () -> Unit = {}
) {
    var resetStatsDialogShown by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ActionBar(
                profile = profile,
                onClickBack = onClickBack
            )
        },
        content = {
            Content(
                onClickProfileSettings = onClickProfileSettings,
                onClickResetStats = { resetStatsDialogShown = true }
            )
        }
    )

    ResetStatsDialog(
        show = resetStatsDialogShown,
        onResetStats = onClickResetStats,
        hide = { resetStatsDialogShown = false }
    )
}

@Composable
private fun ActionBar(profile: Resource<Profile>, onClickBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.surface,
                shape = CutCornerShape(
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                )
            )
    ) {
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
                Text(text = "Settings", color = MaterialTheme.colors.primary)
            },
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface,
            elevation = 0.dp
        )
        ProfileInfo(
            profile = profile,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ProfileInfo(profile: Resource<Profile>, modifier: Modifier = Modifier) {
    when (profile) {
        is Resource.Success -> ProfileInfoContent(profile.data, modifier)
        is Resource.Error -> ProfileInfoError(profile.error, modifier)
        is Resource.Loading -> ProfileInfoLoading(modifier)
    }
}

@Composable
private fun ProfileInfoContent(profile: Profile, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(
                top = 8.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
        ) {
            ProfileImageWithExp(
                diameter = 64.dp,
                imageUrl = profile.imageUrl,
                exp = profile.exp,
                maxExp = profile.maxExp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = profile.name,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${profile.level} lvl, ${profile.exp}/${profile.maxExp} exp",
                color = MaterialTheme.colors.text,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Composable
private fun ProfileInfoLoading(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(
                top = 8.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ProfileInfoError(error: Throwable, modifier: Modifier = Modifier) {
    // TODO
    ProfileInfoLoading(modifier = modifier)
}

@ExperimentalMaterialApi
@Composable
private fun Content(
    onClickProfileSettings: () -> Unit,
    onClickResetStats: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        ListItem(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable(onClick = onClickProfileSettings),
            text = {
                Text(text = "Profile settings")
            },
            trailing = {
                IconButton(onClick = onClickProfileSettings) {
                    Icon(
                        imageVector = Icons.Sharp.NavigateNext,
                        contentDescription = "navigate to profile settings"
                    )
                }
            }
        )
        /*ListItem( TODO
            text = {
                Text(text = "Notifications")
            },
            trailing = {
                IconButton(onClick = { *//*TODO*//* }) {
                    Icon(
                        imageVector = Icons.Sharp.NavigateNext,
                        contentDescription = "navigate to notifications settings"
                    )
                }
            }
        )*/
        Divider(
            modifier = Modifier
                .padding(
                    start = 88.dp,
                    end = 88.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                ),
            color = MaterialTheme.colors.disabled,
            thickness = 2.dp
        )
        ListItem(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable(onClick = onClickResetStats),
            text = {
                Text(text = "Reset stats", color = MaterialTheme.colors.warning)
            }
        )
    }
}



@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun SettingsListScreenPreview() {
    GameOfLifeTheme {
        SettingsListScreen(
            profile = Resource.Success(
                Profile(
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
                ))
        )
    }
}
