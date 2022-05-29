package com.yoloroy.gameoflife.presentation.dreams

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.common.transformMap
import com.yoloroy.gameoflife.domain.model.data.ChallengeWithDreamInfo
import com.yoloroy.gameoflife.domain.model.data.Dream
import com.yoloroy.gameoflife.domain.use_case.CompleteChallenge
import com.yoloroy.gameoflife.domain.use_case.GetCurrentChallengesWithDreamInfo
import com.yoloroy.gameoflife.domain.use_case.GetCurrentDreams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamsViewModel @Inject constructor(
    private val completeChallenge: CompleteChallenge,
    private val getCurrentDreams: GetCurrentDreams,
    private val getCurrentChallengesWithDreamInfo: GetCurrentChallengesWithDreamInfo
) : ViewModel() {

    var dreamsState: Resource<List<Dream>> by mutableStateOf(Resource.Loading())
        private set

    var challengesState: Resource<List<ChallengeCardData>> by mutableStateOf(Resource.Loading())
        private set

    fun completeChallenge(challengeId: String) {
        viewModelScope.launch {
            completeChallenge.invoke(challengeId).collect {} // todo handling
        }
    }

    init {
        viewModelScope.launch {
            getCurrentDreams().collect {
                dreamsState = it
            }
        }
        viewModelScope.launch {
            getCurrentChallengesWithDreamInfo().collect { res ->
                challengesState = res.transformMap { it.toCardData() }
            }
        }
    }
}

private fun ChallengeWithDreamInfo.toCardData() =
    ChallengeCardData(dreamName, id, no, name, description)
