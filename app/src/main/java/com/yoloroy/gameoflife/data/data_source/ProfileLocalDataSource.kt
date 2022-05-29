package com.yoloroy.gameoflife.data.data_source

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.model.data.ProfileDetails
import kotlinx.coroutines.flow.Flow

interface ProfileLocalDataSource {

    val profile: Flow<Resource<Profile>>

    val profileDetails: Flow<Resource<ProfileDetails>>

    fun resetStats(): Flow<Resource<Boolean>>

    fun updateLogin(login: String): Flow<Resource<Boolean>>
}
