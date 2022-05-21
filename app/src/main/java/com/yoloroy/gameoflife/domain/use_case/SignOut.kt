package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.AuthRepository
import javax.inject.Inject

class SignOut @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.signOut()
}
