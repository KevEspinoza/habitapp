package com.kevinespinoza.habitapp.authentication.domain.usecase

data class LoginUseCases(
    val loginWithEmailUseCase: LoginWithEmailUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)
