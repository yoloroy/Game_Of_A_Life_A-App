package com.yoloroy.gameoflife.domain.repository

import com.yoloroy.gameoflife.common.Resource
import kotlinx.coroutines.flow.Flow

interface ProfileSettingsRepository {

    suspend fun getInitialUsername(): String

    fun confirmChanges(): Flow<Resource<Unit>>
}
