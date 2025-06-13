package com.kevinespinoza.habitapp.onboarding.domain.usecase

import com.kevinespinoza.habitapp.onboarding.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke() {
        repository.completeOnboarding()
    }
}