package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ProfileRepository

class GetProfileDetails(val repository: ProfileRepository) {
    operator fun invoke() = repository.profileDetails
}
