package com.yoloroy.gameoflife.presentation.dreams

import androidx.lifecycle.*
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.common.transform
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

    private var _dreams: MutableLiveData<Resource<List<Dream>>> =
        MutableLiveData(Resource.Loading())
    val dreams: LiveData<Resource<List<Dream>>> = _dreams

    private var _challenges: MutableLiveData<Resource<List<ChallengeWithDreamInfo>>> =
        MutableLiveData(Resource.Loading())
    val challenges: LiveData<Resource<List<ChallengeCardData>>> = _challenges.toCardData()

    fun completeChallenge(challengeId: String) {
        val dreamId = _challenges.value?.data?.find { it.id == challengeId }?.dreamId!!

        completeChallenge.invoke(dreamId)
    }

    init {
        viewModelScope.launch {
            getCurrentDreams().collect {
                _dreams.value = it
            }
        }
        viewModelScope.launch {
            getCurrentChallengesWithDreamInfo().collect {
                _challenges.value = it
            }
        }
    }
}

private fun LiveData<Resource<List<ChallengeWithDreamInfo>>>.toCardData() = Transformations
    .map(this) { resource ->
        resource.transform { list ->
            list.map { it.toCardData() }
        }
    }

private fun ChallengeWithDreamInfo.toCardData() =
    ChallengeCardData(dreamName, id, no, name, description)
