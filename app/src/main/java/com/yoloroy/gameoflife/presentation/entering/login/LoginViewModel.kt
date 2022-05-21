package com.yoloroy.gameoflife.presentation.entering.login

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _emailOrLogin: MutableLiveData<String> = MutableLiveData("")
    val emailOrLogin: LiveData<String> = _emailOrLogin

    private val _password: MutableLiveData<String> = MutableLiveData("")
    val password: LiveData<String> = _password

    fun updateEmailOrLogin(emailOrLogin: String) {
        _emailOrLogin.value = emailOrLogin
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun signIn(onSuccess: () -> Unit, onFailure: () -> Unit) {
    }
}

fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
