package com.example.mvvm.ui.login.ui

import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginViewModelTest {
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel()
    }

    @Test
    fun `email and password are empty on launch`() {
        assert(viewModel.email.value.isEmpty())
        assert(viewModel.password.value.isEmpty())
    }

    @Test
    fun `login button is not enabled on launch`() {
        assert(!viewModel.enabled.value)
    }

    @Test
    fun `the password value updates when the event is received`() {
        val newPassword = "myValidPassword"
        viewModel.onLoginChanged(
            newEmail = "", // notice this is awkward to test when coupled
            newPassword = newPassword,
        )
        assertEquals(newPassword, viewModel.password.value)
    }

    @Test
    fun `a non-valid e-mail is not considered valid`() {
        val emailIsValidExpected = false
        val emailIsValidActual = viewModel.isValidEmail("bloop")

        assertEquals(emailIsValidExpected, emailIsValidActual)
    }

    @Test
    fun `a valid e-mail is considered valid`() {
        val emailIsValidExpected = true
        val emailIsValidActual = viewModel.isValidEmail("bloop@gmail.com")

        assertEquals(emailIsValidExpected, emailIsValidActual)
    }

    @Test
    fun `when an email and password are entered, the button to submit is enabled`() {
        val newEmail = "myValidEmail@gmail.com"
        val newPassword = "myValidPassword"

        viewModel.onLoginChanged(
            newEmail = newEmail,
            newPassword = newPassword,
        )

        assert(viewModel.enabled.value)
    }
}