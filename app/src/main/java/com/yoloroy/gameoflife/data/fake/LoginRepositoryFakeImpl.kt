package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.domain.repository.LoginRepository
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

object LoginRepositoryFakeImpl : LoginRepository {
    override suspend fun signInByEmail(email: String, password: String): Boolean {
        delay(1.seconds)
        return email == "em@i.l" && password == "password"
    }

    override suspend fun signInByLogin(login: String, password: String): Boolean {
        delay(1.seconds)
        return login == "login" && password == "password"
    }
}