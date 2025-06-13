package com.kevinespinoza.habitapp.onboarding.domain.repository

interface OnboardingRepository {
    fun hasSeenOnboarding(): Boolean
    fun completeOnboarding()
}