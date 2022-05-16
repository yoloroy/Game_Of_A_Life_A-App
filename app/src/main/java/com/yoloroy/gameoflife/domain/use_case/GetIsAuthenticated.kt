package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.AuthRepository

class GetIsAuthenticated(val repository: AuthRepository) {
    suspend operator fun invoke() = repository.getIsAuthenticated()
}

