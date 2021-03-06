package com.yoloroy.gameoflife.domain.repository

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.model.data.ProfileDetails
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    val profileDetails: Flow<Resource<ProfileDetails>>

    val profile: Flow<Resource<Profile>>

    fun resetStats(): Flow<Resource<Boolean>>

    fun updateLogin(login: String): Flow<Resource<Boolean>>
}
