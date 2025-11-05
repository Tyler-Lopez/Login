package com.example.mvvm.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _destinations = Channel<LoginDestination>(Channel.BUFFERED)
    val destinations = _destinations.receiveAsFlow()

    private val _email = MutableStateFlow("") // crea un flujo mutable
    val email = _email.asStateFlow() //inmutable creamos ese puente para que la UI pueda verlo
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _enabled = MutableStateFlow(false)
    val enabled = _enabled.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun onClickedForgotPassword() {
        viewModelScope.launch {
            _destinations.send(element = LoginDestination.ForgotPassword)
        }
    }

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


    fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isValidPassword(password: String): Boolean = password.length > 6

    suspend fun pressButton() {
        _loading.value = true
        delay(4000)
        _loading.value = false
    }


}
