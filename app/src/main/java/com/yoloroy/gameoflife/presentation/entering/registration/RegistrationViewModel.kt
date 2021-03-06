package com.yoloroy.gameoflife.presentation.entering.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() : ViewModel() {

    private val _email: MutableLiveData<String> = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _login: MutableLiveData<String> = MutableLiveData("")
    val login: LiveData<String> = _login

    private val _password: MutableLiveData<String> = MutableLiveData("")
    val password: LiveData<String> = _password

    fun onEmailUpdate(email: String) {
        _email.value = email
    }

    fun onLoginUpdate(login: String) {
        _login.value = login
    }

    fun onPasswordUpdate(password: String) {
        _password.value = password
    }

    fun register(email: String, login: String, password: String, onSuccess: () -> Unit) {
        // TODO
    }
}
