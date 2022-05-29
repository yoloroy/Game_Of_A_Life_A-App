package com.yoloroy.gameoflife.data.local

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.data.data_source.ProfileLocalDataSource
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.model.data.ProfileDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomProfileLocalDataSourceImpl @Inject constructor() : ProfileLocalDataSource {

    override val profile: Flow<Resource<Profile>>
        get() = TODO("Not yet implemented")

    override val profileDetails: Flow<Resource<ProfileDetails>>
        get() = TODO("Not yet implemented")

    override fun resetStats(): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun updateLogin(login: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}