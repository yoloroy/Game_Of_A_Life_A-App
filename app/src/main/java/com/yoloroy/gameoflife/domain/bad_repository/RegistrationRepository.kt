package com.yoloroy.gameoflife.domain.bad_repository

interface RegistrationRepository {

    suspend fun register(email: String, login: String, password: String): Boolean
}