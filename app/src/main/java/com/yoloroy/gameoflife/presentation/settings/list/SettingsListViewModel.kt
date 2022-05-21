package com.yoloroy.gameoflife.presentation.settings.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.use_case.GetProfile
import com.yoloroy.gameoflife.domain.use_case.ResetStats
import com.yoloroy.gameoflife.domain.use_case.SignOut
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsListViewModel @Inject constructor(
    private val getProfile: GetProfile,
    private val resetStats: ResetStats,
    private val signOut: SignOut
) : ViewModel() {

    var profile: Resource<Profile> by mutableStateOf(Resource.Loading())
        private set

    init {
        viewModelScope.launch {
            getProfile().collect {
                profile = it
            }
        }
    }

    fun resetStats(onSuccess: () -> Unit) {
        viewModelScope.launch {
            resetStats().collect {
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
            signOut().collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> onSuccess()
                    is Resource.Error -> TODO()
                }
            }
        }
    }
}
