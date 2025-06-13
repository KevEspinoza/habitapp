package com.kevinespinoza.habitapp.authentication.presentation.signup

sealed interface SignUpEvent {
    data class EmailChange(val email: String): SignUpEvent
    data class PasswordChange(val password: String): SignUpEvent
    data object LogIn: SignUpEvent
    data object SignUp: SignUpEvent
}