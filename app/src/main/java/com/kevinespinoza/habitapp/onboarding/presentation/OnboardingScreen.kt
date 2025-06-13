package com.kevinespinoza.habitapp.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.kevinespinoza.habitapp.R
import com.kevinespinoza.habitapp.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(
    viewmodel: OnboardingViewModel = hiltViewModel(),
    onFinish: () -> Unit,
){
    LaunchedEffect(key1 = viewmodel.hasSeenOnboarding) {
        if(viewmodel.hasSeenOnboarding){
            onFinish()
        }
    }
    val pages = listOf(
        OnboardingPagerInformation(
            title = "Welcome to Monumental Habits",
            subtitle = "We can help you to be better version of yourself",
            image = R.drawable.onboarding1,
        ),
        OnboardingPagerInformation(
            title = "Create new habit easily",
            subtitle = "We can help you to be better version of yourself",
            image = R.drawable.onboarding2,
        ),
        OnboardingPagerInformation(
            title = "KEEP TRACK OF YOUR PROGRESS",
            subtitle = "We can help you to be better version of yourself",
            image = R.drawable.onboarding3,
        ),
        OnboardingPagerInformation(
            title = "JOIN A SUPPORTIVE COMMUNITY",
            subtitle = "We can help you to be better version of yourself",
            image = R.drawable.onboarding4,
        ),
    )

    OnboardingPager(pages = pages, onFinish = {
        viewmodel.completeOnboarding()
    })
}