package com.yoloroy.gameoflife.domain.model.codes

sealed class SignInResult {
    object NoSuchUser : SignInResult()
    object WrongPassword : SignInResult()
    class Success(val userId: String) : SignInResult()
}
