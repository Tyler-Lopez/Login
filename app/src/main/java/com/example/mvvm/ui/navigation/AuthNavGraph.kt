package com.example.mvvm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mvvm.ui.forgotPassword.ForgotPasswordScreen
import com.example.mvvm.ui.login.ui.ForgotPassword
import com.example.mvvm.ui.login.ui.LoginDestination
import com.example.mvvm.ui.login.ui.LoginScreen
import com.example.mvvm.ui.login.ui.LoginViewModel
import com.example.mvvm.ui.registration.RegistrationScreen

@Composable
internal fun AuthNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreen.Login.route,
    ) {
        composable(route = AuthScreen.Login.route) { backStackEntry ->
            val viewModel: LoginViewModel = viewModel(viewModelStoreOwner = backStackEntry)

            LoginScreen(
                viewModel = viewModel,
            )

            LaunchedEffect(viewModel) {
                viewModel.destinations.collect { event ->
                    when (event) {
                        LoginDestination.ForgotPassword ->
                            navController.navigate(route = AuthScreen.ForgotPassword.route)
                    }
                }
            }

        }
        composable(route = AuthScreen.ForgotPassword.route) { backStackEntry ->
            ForgotPasswordScreen()
        }
        composable(route = AuthScreen.Registration.route) { backStackEntry ->
            RegistrationScreen()
        }
    }
}