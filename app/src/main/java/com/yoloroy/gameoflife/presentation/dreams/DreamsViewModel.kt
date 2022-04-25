package com.yoloroy.gameoflife.presentation.dreams

import androidx.lifecycle.*
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.common.transform
import com.yoloroy.gameoflife.domain.model.ChallengeWithDreamInfo
import com.yoloroy.gameoflife.domain.model.Dream
import com.yoloroy.gameoflife.domain.repository.DreamsRepository
import kotlinx.coroutines.launch

class DreamsViewModel(private val dreamsRepository: DreamsRepository) : ViewModel() {

    private var _dreams: MutableLiveData<Resource<List<Dream>, String?>> =
        MutableLiveData(Resource.Loading())
    val dreams: LiveData<Resource<List<Dream>, String?>> = _dreams

    private var _challenges: MutableLiveData<Resource<List<ChallengeWithDreamInfo>, String?>> =
        MutableLiveData(Resource.Loading())
    val challenges: LiveData<Resource<List<ChallengeCardData>, String?>> = _challenges.toCardData()

    fun completeChallenge(challengeId: String) {
        val dreamId = _challenges.value?.data?.find { it.id == challengeId }?.dreamId!!

        dreamsRepository.completeChallenge(dreamId)
    }

    init {
        viewModelScope.launch {
            _dreams.value = try {
                Resource.Success(dreamsRepository.getDreams())
            } catch (e: Exception) { // TODO
                Resource.Error(e.localizedMessage)
            }
        }
        viewModelScope.launch {
            _challenges.value = try {
                Resource.Success(dreamsRepository.getCurrentChallenges())
            } catch (e: Exception) { // TODO
                Resource.Error(e.localizedMessage)
            }
        }
    }
}

private fun LiveData<Resource<List<ChallengeWithDreamInfo>, String?>>.toCardData() = Transformations
    .map(this) { resource ->
        resource.transform { list ->
            list.map(ChallengeWithDreamInfo::toCardData)
        }
    }

private fun ChallengeWithDreamInfo.toCardData() =
    ChallengeCardData(dreamName, id, no, name, description)
