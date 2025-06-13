package com.kevinespinoza.habitapp.authentication.domain.usecase

data class SignupUseCases(
    val signupWithEmailUseCase: SignupWithEmailUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)
