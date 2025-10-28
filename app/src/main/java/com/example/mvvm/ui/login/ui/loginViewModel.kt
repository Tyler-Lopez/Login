package com.example.mvvm.ui.login.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(value = LoginViewState(email = ""))

    // THESE ARE OUR PUBLIC THINGS
    val state2 = _state.asStateFlow()

    fun onEvent(event: LoginViewEvent) {
        when (event) {
            is LoginViewEvent.ChangedEmailText -> onChangedEmailText(event = event)
        }
    }

    private fun onChangedEmailText(event: LoginViewEvent.ChangedEmailText) {
        val nuevaString = event.nuevaString

        // The View just told us there is a new string
        // but what do we do with it?

        println("Nueva string is $nuevaString")

        _state.update {
            it.copy(
                email = nuevaString,
            )
        }
    }
}

// The View talks to the ViewModel with this
sealed interface LoginViewEvent {
    data class ChangedEmailText(val nuevaString: String) : LoginViewEvent
}

// The ViewModel talks to the View with this
data class LoginViewState(
    val email: String,
)