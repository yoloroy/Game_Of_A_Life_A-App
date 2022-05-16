package com.yoloroy.gameoflife.domain.use_case

import com.yoloroy.gameoflife.domain.repository.AuthRepository

class SignIn(val repository: AuthRepository) {
    operator fun invoke(email: String, password: String) =
        repository.signIn(email, password)
}
