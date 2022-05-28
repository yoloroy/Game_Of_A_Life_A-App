package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfile @Inject constructor(private val repository: ProfileRepository) {
    operator fun invoke() = repository.profile
}
