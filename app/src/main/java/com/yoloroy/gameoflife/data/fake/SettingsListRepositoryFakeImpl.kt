package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.bad_repository.SettingsListRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

object SettingsListRepositoryFakeImpl : SettingsListRepository {
    override val profile by ProfileRepositoryFakeImpl::profile

    override fun resetStats() = flow {
        emit(Resource.Loading())
        delay(1.seconds)
        emit(Resource.Success(Unit))
    }

    override fun signOut() = flow {
        emit(Resource.Loading())
        delay(1.seconds)
        emit(Resource.Success(Unit))
    }
}
