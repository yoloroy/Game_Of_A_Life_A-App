package com.yoloroy.gameoflife.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.ProfileDetails
import com.yoloroy.gameoflife.domain.use_case.GetProfileDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val getProfileDetails: GetProfileDetails) : ViewModel() {

    var profile: Resource<ProfileDetails> by mutableStateOf(Resource.Loading())
        private set

    init {
        viewModelScope.launch {
            getProfileDetails().collect {
                profile = it
            }
        }
    }
}
