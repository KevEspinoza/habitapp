package com.kevinespinoza.habitapp.onboarding.data.repository

import android.content.SharedPreferences
import com.kevinespinoza.habitapp.onboarding.domain.repository.OnboardingRepository
import com.kevinespinoza.habitapp.onboarding.domain.usecase.HasSeenOnboardingUseCase

class OnboardingRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : OnboardingRepository {

    companion object {
        private const val HAS_SEEN_ONBOARDING = "has_seen_onboarding"
    }

    override fun hasSeenOnboarding(): Boolean {
        return sharedPreferences.getBoolean(HAS_SEEN_ONBOARDING, false)
    }

    override fun completeOnboarding() {
        sharedPreferences.edit().putBoolean(HAS_SEEN_ONBOARDING, true).apply()
    }
}