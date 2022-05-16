package com.yoloroy.gameoflife.data.fake

import com.yoloroy.gameoflife.common.Resource
import com.yoloroy.gameoflife.domain.model.codes.*
import com.yoloroy.gameoflife.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

object AuthRepositoryFakeImpl : AuthRepository {

    override suspend fun getIsAuthenticated(): Boolean {
        delay(2.seconds)
        return true
    }

    override fun signIn(email: String, password: String): Flow<Resource<SignInResult>> = flow {
        emit(Resource.Error(NotImplementedError("Not yet implemented")))
    }

    override fun signUp(
        username: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<Resource<SignUpResult>> = flow {
        emit(Resource.Error(NotImplementedError("Not yet implemented")))
    }

    override fun signOut(): Flow<Resource<SignOutResult>> = flow {
        emit(Resource.Error(NotImplementedError("Not yet implemented")))
    }

    override fun updatePassword(
        oldPassword: String,
        newPassword: String,
        newPasswordConfirmation: String
    ): Flow<Resource<UpdatePasswordResult>> = flow {
        emit(Resource.Error(NotImplementedError("Not yet implemented")))
    }

    override fun updateEmail(newEmail: String): Flow<Resource<UpdateEmailResult>> = flow {
        emit(Resource.Error(NotImplementedError("Not yet implemented")))
    }
}
