package com.kevinespinoza.habitapp.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinespinoza.habitapp.authentication.domain.usecase.LoginUseCases
import com.kevinespinoza.habitapp.authentication.domain.usecase.PasswordResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    var state by mutableStateOf(LoginStates())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChange -> {
                state = state.copy(
                    email = event.email
                )
            }

            LoginEvent.Login -> {
                login()
            }

            is LoginEvent.PasswordChange -> {
                state = state.copy(
                    password = event.password
                )
            }
        }
    }

    fun stopToast() {
        state = state.copy(
            toastError = null
        )
    }

    private fun login() {
        state = state.copy(
            emailError = null,
            passwordError = null,
            toastError = null,
        )
        if (!loginUseCases.validateEmailUseCase(state.email)) {
            state = state.copy(
                emailError = "Invalid email"
            )
        }

        val passwordResult = loginUseCases.validatePasswordUseCase(state.password)

        if (passwordResult is PasswordResult.Invalid) {
            state = state.copy(
                passwordError = passwordResult.errorMessage,
                isLoading = false
            )
        }

        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(
                isLoading = true
            )
            viewModelScope.launch {
                loginUseCases.loginWithEmailUseCase(state.email, state.password).onSuccess {
                    state = state.copy(
                        isLoggedIn = true
                    )
                }.onFailure {
                    state = state.copy(
                        toastError = it.message
                    )
                }
                state = state.copy(
                    isLoading = true
                )
            }
        }
    }
}