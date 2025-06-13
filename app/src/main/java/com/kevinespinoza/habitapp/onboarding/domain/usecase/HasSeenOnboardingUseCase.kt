package com.kevinespinoza.habitapp.onboarding.domain.usecase

import com.kevinespinoza.habitapp.onboarding.domain.repository.OnboardingRepository

class HasSeenOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke(): Boolean {
        return repository.hasSeenOnboarding()
    }
}