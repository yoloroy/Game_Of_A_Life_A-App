package com.yoloroy.gameoflife.presentation.login

fun interface LoginCallback {
    fun login(emailOrLogin: String, password: String)
}
