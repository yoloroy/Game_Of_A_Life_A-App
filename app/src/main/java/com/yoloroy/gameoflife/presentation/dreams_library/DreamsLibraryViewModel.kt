package com.yoloroy.gameoflife.presentation.dreams_library

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.use_case.GetDreamsByTags
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamsLibraryViewModel @Inject constructor(
    private val getDreamsByTags: GetDreamsByTags,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var tags by mutableStateOf(handleSavedTags(savedStateHandle))
        private set

    var dreams by mutableStateOf<Resource<List<Dream>>>(Resource.Loading())
        private set

    @Suppress("SuspiciousCollectionReassignment")
    fun addTag(tag: String) {
        tags += tag
        onUpdateTags()
    }

    @Suppress("SuspiciousCollectionReassignment")
    fun removeTag(tag: String) {
        tags -= tag
        onUpdateTags()
    }

    private fun onUpdateTags() {
        viewModelScope.launch {
            getDreamsByTags(tags.toList())
                .collect { dreams = it }
        }
    }

    init {
        onUpdateTags()
    }

    private fun handleSavedTags(savedStateHandle: SavedStateHandle) =
        (savedStateHandle.get<String>("tags") ?: "")
            .split(",")
            .filter(String::isNotEmpty)
            .toSet()
}
