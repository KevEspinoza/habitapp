package com.kevinespinoza.habitapp.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import com.kevinespinoza.habitapp.onboarding.data.repository.OnboardingRepositoryImpl
import com.kevinespinoza.habitapp.onboarding.domain.repository.OnboardingRepository
import com.kevinespinoza.habitapp.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.kevinespinoza.habitapp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("habits_onboarding_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(sharedPreferences: SharedPreferences): OnboardingRepository {
        return OnboardingRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideHasSeenOnboardingUseCase(repository: OnboardingRepository): HasSeenOnboardingUseCase{
        return HasSeenOnboardingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCompleteOnboardingUseCase(repository: OnboardingRepository): CompleteOnboardingUseCase{
        return CompleteOnboardingUseCase(repository)
    }
}