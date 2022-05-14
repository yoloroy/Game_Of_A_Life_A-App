package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.domain.repository.RegistrationRepository

object RegistrationRepositoryFakeImpl : RegistrationRepository {

    override suspend fun register(email: String, login: String, password: String) =
        email == "em@i.l" &&
        login == "login" &&
        password == "password"
}