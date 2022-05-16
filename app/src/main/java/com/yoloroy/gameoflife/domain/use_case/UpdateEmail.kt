package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.AuthRepository

class UpdateEmail(val repository: AuthRepository) {
    operator fun invoke(newEmail: String) = repository.updateEmail(newEmail)
}
