package com.yoloroy.gameoflife.presentation.dream_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.DreamDetail
import com.yoloroy.gameoflife.domain.model.data.DreamStatus
import com.yoloroy.gameoflife.domain.use_case.GetDreamCurrentStatus
import com.yoloroy.gameoflife.domain.use_case.GetDreamDetail
import com.yoloroy.gameoflife.domain.use_case.SubscribeOnDream
import com.yoloroy.gameoflife.domain.use_case.UnsubscribeFromDream
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamDetailsViewModel @Inject constructor(
    private val getDreamDetail: GetDreamDetail,
    private val getDreamCurrentStatus: GetDreamCurrentStatus,
    private val subscribeOnDream: SubscribeOnDream,
    private val unsubscribeFromDream: UnsubscribeFromDream,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val dreamId = savedStateHandle.get<String>("id")!!

    var mode: DreamDetailsMode by mutableStateOf(DreamDetailsMode.View)
        private set
    var dream: Resource<DreamDetail> by mutableStateOf(Resource.Loading())
        private set

    init {
        viewModelScope.launch {
            getDreamDetail(dreamId)
                .collect { dream = it }
        }
        viewModelScope.launch {
            getDreamCurrentStatus(dreamId)
                .map {
                    when (it) {
                        is Resource.Loading -> mode
                        is Resource.Success -> when (it.data!!) {
                            is DreamStatus.Subscribed -> DreamDetailsMode.Removing
                            DreamStatus.Finished -> DreamDetailsMode.View
                            is DreamStatus.InProcess -> DreamDetailsMode.Removing // TODO add "are you sure" dialog
                            DreamStatus.None -> DreamDetailsMode.Adding
                        }
                        is Resource.Error -> DreamDetailsMode.View // TODO error catching
                    }
                }
                .collect { mode = it }
        }
    }

    fun addDream() { // TODO rename
        viewModelScope.launch {
            subscribeOnDream(dreamId).collect() // TODO result handling
        }
    }

    fun removeDream() { // TODO rename
        viewModelScope.launch {
            unsubscribeFromDream(dreamId).collect() // TODO result handling
        }
    }
}
