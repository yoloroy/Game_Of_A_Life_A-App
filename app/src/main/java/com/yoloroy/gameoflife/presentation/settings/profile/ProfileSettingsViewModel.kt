package com.yoloroy.gameoflife.presentation.settings.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.use_case.GetProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSettingsViewModel @Inject constructor(
    private val getProfile: GetProfile
) : ViewModel() {

    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var previousPassword by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    init {
        viewModelScope.launch {
            getProfile()
                .filterIsInstance<Resource.Success<Profile>>()
                .map { it.data!! }
                .collect {
                    username = it.name
                }
        }
    }

    fun confirmChanges(onSuccess: () -> Unit) {
        // TODO
    }
}
