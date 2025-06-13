package com.kevinespinoza.habitapp.authentication.domain.usecase

import com.kevinespinoza.habitapp.authentication.domain.repository.AuthenticationRepository

class LoginWithEmailUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}