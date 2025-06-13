package com.kevinespinoza.habitapp.onboarding.presentation

import androidx.annotation.DrawableRes

data class OnboardingPagerInformation(
    val title: String,
    val subtitle: String,
    @DrawableRes val image: Int,
)
