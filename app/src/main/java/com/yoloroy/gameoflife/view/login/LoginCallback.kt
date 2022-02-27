package com.yoloroy.gameoflife.view.login

fun interface LoginCallback {
    fun login(emailOrLogin: String, password: String)
}
