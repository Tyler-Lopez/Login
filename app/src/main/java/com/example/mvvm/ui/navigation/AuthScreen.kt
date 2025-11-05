package com.example.mvvm.ui.navigation

sealed interface AuthScreen {
    val route: String

    data object ForgotPassword : AuthScreen {
        override val route: String
            get() = "forgot_password"
    }

    data object Login : AuthScreen {
        override val route: String
            get() = "login"
    }

    data object Registration : AuthScreen {
        override val route: String
            get() = "registration"
    }
}