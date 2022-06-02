package com.yoloroy.gameoflife.presentation.dreams_library

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.presentation.components.GoalCard

// TODO: localisation
sealed class LibraryDreamsUi {

    @Composable
    abstract fun Content(onClickTag: (String) -> Unit, onClickDream: (String) -> Unit)

    companion object {
        fun fromResource(dreamsResource: Resource<List<Dream>>) = when (dreamsResource) {
            is Resource.Success -> DreamsContent(dreamsResource.data)
            is Resource.Loading -> dreamsResource.data
                ?.let { LoadingWithSavedDreamsContent(it) }
                ?: LoadingContent
            is Resource.Error -> ErrorContent
        }
    }

    open class DreamsContent(private val dreams: List<Dream>) : LibraryDreamsUi() {

        @Composable
        override fun Content(onClickTag: (String) -> Unit, onClickDream: (String) -> Unit) {
            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "See ${dreams.size} results", // TODO
                        modifier = Modifier.padding(10.dp),
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
        }
    }

    class LoadingWithSavedDreamsContent(dreams: List<Dream>) : LibraryDreamsUi() {

        private val dreamsContent = DreamsContent(dreams)

        @Composable
        override fun Content(onClickTag: (String) -> Unit, onClickDream: (String) -> Unit) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
                dreamsContent.Content(onClickTag, onClickDream)
            }
        }
    }

    object LoadingContent : LibraryDreamsUi() {

        @Composable
        override fun Content(
            onClickTag: (String) -> Unit,
            onClickDream: (String) -> Unit
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Loading results..", // TODO
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.subtitle2
                )

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
    }

    object ErrorContent : LibraryDreamsUi() {

        @Composable
        override fun Content(
            onClickTag: (String) -> Unit,
            onClickDream: (String) -> Unit
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Something went wrong", // TODO
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
        }
    }
}
