package com.yoloroy.gameoflife.domain.bad_repository

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface SettingsListRepository {

    val profile: Profile

    fun resetStats(): Flow<Resource<Unit>>

    fun signOut(): Flow<Resource<Unit>>
}
