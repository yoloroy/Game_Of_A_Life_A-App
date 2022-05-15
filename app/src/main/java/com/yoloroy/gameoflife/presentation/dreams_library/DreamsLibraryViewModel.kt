package com.yoloroy.gameoflife.presentation.dreams_library

import androidx.lifecycle.*
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.bad_repository.DreamsLibraryRepository
import com.yoloroy.gameoflife.domain.model.Dream
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamsLibraryViewModel @Inject constructor(
    private val repository: DreamsLibraryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _tags: MutableLiveData<Set<String>> = MutableLiveData(
        savedStateHandle.get<Array<String>>("tags")?.toSet() ?: emptySet()
    )
    val tags: LiveData<Set<String>> = _tags

    private val _dreams: MutableLiveData<Resource<List<Dream>>> = MutableLiveData(Resource.Loading())
    val dreams: LiveData<Resource<List<Dream>>> = _dreams

    fun addTag(tag: String) {
        _tags.value = _tags.value!! + tag
    }

    fun removeTag(tag: String) {
        _tags.value = _tags.value!! - tag
    }

    private val tagsObserver = Observer<Set<String>> { tags ->
        viewModelScope.launch {
            repository
                .getDreamsByTags(tags!!)
                .collect { _dreams.value = it }
        }
    }

    init {
        _tags.observeForever(tagsObserver)
    }

    override fun onCleared() {
        _tags.removeObserver(tagsObserver)
        super.onCleared()
    }
}
