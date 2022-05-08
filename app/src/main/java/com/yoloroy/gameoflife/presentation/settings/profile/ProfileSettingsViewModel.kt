package com.yoloroy.gameoflife.presentation.settings.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.repository.ProfileSettingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfileSettingsViewModel(val repository: ProfileSettingsRepository) : ViewModel() {

    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var previousPassword by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    init {
        viewModelScope.launch {
            username = repository.getInitialUsername()
        }
    }

    private var confirmationJob: Job? = null
    fun confirmChanges(onSuccess: () -> Unit) {
        viewModelScope
            .launch {
                repository.confirmChanges().collect {
                    when (it) {
                        is Resource.Success -> onSuccess() // TODO result enum
                        else -> TODO()
                    }
                }
            }
            .apply {
                invokeOnCompletion {
                    confirmationJob = null
                }
                confirmationJob = this
            }
    }
}
