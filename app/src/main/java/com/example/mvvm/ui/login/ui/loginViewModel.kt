package com.example.mvvm.ui.login.ui

import android.util.Patterns
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("") // crea un flujo mutable
    val email = _email.asStateFlow() //inmutable creamos ese puente para que la UI pueda verlo
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _enabled = MutableStateFlow(false)
    val enabled = _enabled.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun onLoginChanged(
        newEmail: String,
        newPassword: String
    ) { // Es lo que quiero que haga, en este caso que actualice
        _email.value =
            newEmail // _email es como un puntero de MutableStateFlow que es un contenedor de estado observable, value representa el estado actual
        // que contiene , en este caso lo inicialicé en "", así que le entregará el nuevo valor que es newEmail, haciendo que se actualice.
        _password.value = newPassword
        _enabled.value = isValidEmail(newEmail) && isValidPassword(newPassword)

    }

    @VisibleForTesting fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    @VisibleForTesting fun isValidPassword(password: String): Boolean = password.length > 6

    fun pressButton() {
        viewModelScope.launch {
            _loading.value = true
            delay(4000)
            _loading.value = false
        }
    }
}
