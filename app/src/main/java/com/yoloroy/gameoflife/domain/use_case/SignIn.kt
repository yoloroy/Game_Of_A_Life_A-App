package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.AuthRepository
import javax.inject.Inject

class SignIn @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(email: String, password: String) =
        repository.signIn(email, password)
}
