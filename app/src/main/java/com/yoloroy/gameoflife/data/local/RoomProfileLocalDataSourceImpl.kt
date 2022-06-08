package com.yoloroy.gameoflife.data.local

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.data.data_source.ProfileLocalDataSource
import com.yoloroy.gameoflife.data.local.dao.ProfileDao
import com.yoloroy.gameoflife.data.local.entity.ProfileFull
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.model.data.ProfileDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.yoloroy.gameoflife.data.local.entity.Profile as ProfileEntity

class RoomProfileLocalDataSourceImpl @Inject constructor(
    private val profileDao: ProfileDao
) : ProfileLocalDataSource {

    override val profile: Flow<Resource<Profile>> get() = profileDao.getProfile()
        .mapToResource(ProfileEntity::toDomainProfile)

    override val profileDetails: Flow<Resource<ProfileDetails>> get() = profileDao.getProfileFull()
        .mapToResource(ProfileFull::toProfileDetails)

    override fun resetStats(): Flow<Resource<Boolean>> = flow {
        profileDao.resetProfileDreams()
        profileDao.resetProfileStats()

        emit(Resource.Success(true))
    }

    override fun updateLogin(login: String): Flow<Resource<Boolean>> = flow {
        profileDao.updateName(login)

        emit(Resource.Success(true))
    }
}
