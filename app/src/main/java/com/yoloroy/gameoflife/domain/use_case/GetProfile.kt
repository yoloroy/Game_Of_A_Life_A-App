package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.common.transform
import com.yoloroy.gameoflife.domain.model.data.Profile
import com.yoloroy.gameoflife.domain.model.data.ProfileDetails
import com.yoloroy.gameoflife.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetProfile @Inject constructor(private val repository: ProfileRepository) {
    operator fun invoke() = repository.profileDetails
        .transform<Resource<ProfileDetails>, Resource<Profile>> { res -> res.transform { Profile(it) } }
}
