package com.yoloroy.gameoflife.presentation.registration

fun interface RegistrationCallback {
    fun register(email: String, login: String, password: String)
}
