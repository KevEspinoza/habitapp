package com.kevinespinoza.habitapp.authentication.domain.usecase

import com.kevinespinoza.habitapp.authentication.domain.matcher.EmailMatcher

class ValidateEmailUseCase(private val emailMatcher: EmailMatcher) {
    operator fun invoke(email: String): Boolean {
        return emailMatcher.isValid(email)
    }
}