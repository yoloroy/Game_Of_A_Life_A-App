package com.yoloroy.gameoflife.presentation.dream_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.DreamDetail
import com.yoloroy.gameoflife.domain.repository.DreamDetailsRepository
import com.yoloroy.gameoflife.presentation.dream_details.DreamDetailsMode.Adding
import com.yoloroy.gameoflife.presentation.dream_details.DreamDetailsMode.Removing
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DreamDetailsViewModel(
    val repository: DreamDetailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val dreamId = savedStateHandle.get<String>("id")!!

    var mode: DreamDetailsMode by mutableStateOf(DreamDetailsMode.View)
        private set
    var dream: Resource<DreamDetail> by mutableStateOf(Resource.Loading())
        private set

    init {
        repository.getDreamById(dreamId)
            .onEach { dream = it }

        repository.getIsSubscribedOnDream(dreamId)
            .map { isSubscribed -> if (isSubscribed) Removing else Adding }
            .onEach { mode = it }
    }

    fun addDream() {
        viewModelScope.launch {
            repository.addDream(dreamId).collect() // TODO result handling
        }
    }

    fun removeDream() {
        viewModelScope.launch {
            repository.removeDream(dreamId).collect() // TODO result handling
        }
    }
}
