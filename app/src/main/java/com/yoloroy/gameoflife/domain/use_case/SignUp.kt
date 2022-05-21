package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(val repository: AuthRepository) {
    operator fun invoke(
        username: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ) = repository.signUp(username, email, password, passwordConfirmation)
}
