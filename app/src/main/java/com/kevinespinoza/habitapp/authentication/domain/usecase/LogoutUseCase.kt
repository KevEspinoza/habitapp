package com.kevinespinoza.habitapp.authentication.domain.usecase

import com.kevinespinoza.habitapp.authentication.domain.repository.AuthenticationRepository

class LogoutUseCase (
    private val repository: AuthenticationRepository
){
    suspend operator fun invoke() {
       return repository.logout()
    }
}