package com.yoloroy.gameoflife.view.registration

fun interface RegistrationCallback {
    fun register(email: String, login: String, password: String)
}
