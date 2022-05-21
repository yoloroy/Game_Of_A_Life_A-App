package com.yoloroy.gameoflife.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.use_case.GetProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val getProfile: GetProfile) : ViewModel() {

    var profile: Resource<Profile> by mutableStateOf(Resource.Loading())
        private set

    init {
        viewModelScope.launch {
            getProfile().collect {
                profile = it
            }
        }
    }
}
