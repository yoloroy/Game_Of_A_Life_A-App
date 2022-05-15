package com.yoloroy.gameoflife.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.yoloroy.gameoflife.domain.bad_repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val repository: ProfileRepository) : ViewModel() {

    val profile by mutableStateOf(repository.profile)
}
