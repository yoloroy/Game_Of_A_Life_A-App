package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.AuthRepository
import javax.inject.Inject

class UpdateEmail @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(newEmail: String) = repository.updateEmail(newEmail)
}
