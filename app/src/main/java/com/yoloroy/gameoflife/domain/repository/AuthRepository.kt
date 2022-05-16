package com.yoloroy.gameoflife.domain.repository

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.codes.*
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun getIsAuthenticated(): Boolean

    fun signIn(email: String, password: String): Flow<Resource<SignInResult>>

    fun signUp(
        username: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<Resource<SignUpResult>>

    fun signOut(): Flow<Resource<SignOutResult>>

    fun updatePassword(
        oldPassword: String,
        newPassword: String,
        newPasswordConfirmation: String
    ): Flow<Resource<UpdatePasswordResult>>

    fun updateEmail(newEmail: String): Flow<Resource<UpdateEmailResult>>
}
