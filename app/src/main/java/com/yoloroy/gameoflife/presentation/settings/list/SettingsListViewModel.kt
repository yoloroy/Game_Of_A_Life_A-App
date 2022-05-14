package com.yoloroy.gameoflife.presentation.settings.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.repository.SettingsListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsListViewModel @Inject constructor(val repository: SettingsListRepository) : ViewModel() {

    var profile by mutableStateOf(repository.profile)
        private set

    fun resetStats(onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.resetStats()
                .collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> onSuccess()
                        is Resource.Error -> TODO()
                    }
                }
        }
    }

    fun signOut(onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.signOut()
                .collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> onSuccess()
                        is Resource.Error -> TODO()
                    }
                }
        }
    }
}
