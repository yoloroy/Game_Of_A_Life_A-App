package com.yoloroy.gameoflife.domain.repository

interface RegistrationRepository {

    suspend fun register(email: String, login: String, password: String): Boolean
}
