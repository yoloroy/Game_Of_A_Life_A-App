package com.yoloroy.gameoflife.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.yoloroy.gameoflife.domain.repository.ProfileRepository

class ProfileViewModel(val repository: ProfileRepository) : ViewModel() {

    val profile by mutableStateOf(repository.profile)
}
