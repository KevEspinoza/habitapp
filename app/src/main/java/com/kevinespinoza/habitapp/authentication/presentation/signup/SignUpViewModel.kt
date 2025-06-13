package com.kevinespinoza.habitapp.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinespinoza.habitapp.authentication.domain.usecase.PasswordResult
import com.kevinespinoza.habitapp.authentication.domain.usecase.SignupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signupUseCases: SignupUseCases
) : ViewModel() {
    var state by mutableStateOf(SignUpState())
        private set

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChange -> {
                state = state.copy(email = event.email)
            }
            is SignUpEvent.PasswordChange -> {
                state = state.copy(password = event.password)
            }
            SignUpEvent.LogIn -> {
                state = state.copy(
                    logIn = true
                )
            }
            SignUpEvent.SignUp -> {
                signUp()
            }
        }
    }

    fun stopToast() {
        state = state.copy(
            toastError = null
        )
    }

    private fun signUp() {
        state = state.copy(
            emailError = null,
            passwordError = null,
            toastError = null,
        )
        if (!signupUseCases.validateEmailUseCase(state.email)) {
            state = state.copy(
                emailError = "Invalid email"
            )
        }

        val passwordResult = signupUseCases.validatePasswordUseCase(state.password)

        if (passwordResult is PasswordResult.Invalid) {
            state = state.copy(
                passwordError = passwordResult.errorMessage
            )
        }

        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(
                isLoading = true
            )
            viewModelScope.launch {
                signupUseCases.signupWithEmailUseCase(state.email, state.password).onSuccess {
                    state = state.copy(
                        isSingedIn = true
                    )
                }.onFailure {
                    state = state.copy(
                        toastError = it.message
                    )
                }
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }
}