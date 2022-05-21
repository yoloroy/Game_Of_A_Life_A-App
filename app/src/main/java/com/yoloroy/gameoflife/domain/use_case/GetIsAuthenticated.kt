package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.AuthRepository
import javax.inject.Inject

class GetIsAuthenticated @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.getIsAuthenticated()
}

