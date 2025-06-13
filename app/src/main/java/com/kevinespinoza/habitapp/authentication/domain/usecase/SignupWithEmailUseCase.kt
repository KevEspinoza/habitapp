package com.kevinespinoza.habitapp.authentication.domain.usecase

import com.kevinespinoza.habitapp.authentication.domain.repository.AuthenticationRepository

class SignupWithEmailUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String) = repository.signup(email, password)
}