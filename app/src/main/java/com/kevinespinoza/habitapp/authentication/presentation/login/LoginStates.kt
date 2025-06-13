package com.kevinespinoza.habitapp.authentication.presentation.login

data class LoginStates(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoggedIn: Boolean = false,
    val toastError: String? = null
)