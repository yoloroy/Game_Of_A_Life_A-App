package com.yoloroy.gameoflife.domain.bad_repository

interface LoginRepository {

    suspend fun signInByEmail(email: String, password: String): Boolean

    suspend fun signInByLogin(login: String, password: String): Boolean
}

// TODO: enum class SignInResult