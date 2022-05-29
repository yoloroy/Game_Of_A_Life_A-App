package com.yoloroy.gameoflife.data.impl

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.data.data_source.ProfileLocalDataSource
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.model.data.ProfileDetails
import com.yoloroy.gameoflife.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val localDataSource: ProfileLocalDataSource
) : ProfileRepository {

    override val profileDetails: Flow<Resource<ProfileDetails>> get() = localDataSource.profileDetails

    override val profile: Flow<Resource<Profile>> get() = localDataSource.profile

    override fun resetStats(): Flow<Resource<Boolean>> = localDataSource.resetStats()

    override fun updateLogin(login: String): Flow<Resource<Boolean>> = localDataSource.updateLogin(login)
}
