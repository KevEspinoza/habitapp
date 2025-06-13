package com.kevinespinoza.habitapp.authentication.domain.usecase

import com.kevinespinoza.habitapp.authentication.domain.repository.AuthenticationRepository

class GetUserIdUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(): String?{
        return authenticationRepository.getUserId()
    }
}