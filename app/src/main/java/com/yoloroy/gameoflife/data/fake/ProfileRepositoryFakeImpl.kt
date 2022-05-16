package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.data.ProfileDetails
import com.yoloroy.gameoflife.domain.repository.ProfileRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

object ProfileRepositoryFakeImpl : ProfileRepository {

    override var profileDetails: Flow<Resource<ProfileDetails>> = flow {
        emit(Resource.Loading())
        FakeSource.profileDetailsFlow.collectLatest {
            emit(Resource.Success(it))
        }
    }

    override fun resetStats(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        delay(1.seconds)
        FakeSource.profileDetails = FakeSource.profileDetails.copy(
            level = 1,
            exp = 0,
            maxExp = 32,
            skills = emptyList(),
            fulfilledDreams = emptyList(),
            ongoingDreams = emptyList()
        )
        emit(Resource.Success(true))
    }

    override fun updateLogin(login: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        delay(1.seconds)
        FakeSource.profileDetails = FakeSource.profileDetails.copy(name = login)
        emit(Resource.Success(true))
    }
}
