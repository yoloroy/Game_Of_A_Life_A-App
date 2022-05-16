package com.yoloroy.gameoflife.domain.model.codes

sealed class SignUpResult {
    object WrongEmail : SignUpResult()
    object UsernameIsTaken : SignUpResult()
    object UnknownError : SignUpResult()
    object ConfirmationDoNotMatch : SignUpResult()
    class Success(val userId: String) : SignUpResult()
}
