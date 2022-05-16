package com.yoloroy.gameoflife.data.bad_fake

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.bad_repository.ProfileSettingsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

object ProfileSettingsFakeRepository : ProfileSettingsRepository {
    override suspend fun getInitialUsername() = "AndroidDancer"

    override fun confirmChanges() = flow {
        emit(Resource.Loading())
        delay(1.seconds)
        emit(Resource.Success(Unit))
    }
}
