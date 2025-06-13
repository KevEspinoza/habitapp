package com.kevinespinoza.habitapp.authentication.di

import com.kevinespinoza.habitapp.authentication.data.matcher.EmailMatcherImpl
import com.kevinespinoza.habitapp.authentication.data.repository.AuthenticationRepositoryImpl
import com.kevinespinoza.habitapp.authentication.domain.matcher.EmailMatcher
import com.kevinespinoza.habitapp.authentication.domain.repository.AuthenticationRepository
import com.kevinespinoza.habitapp.authentication.domain.usecase.GetUserIdUseCase
import com.kevinespinoza.habitapp.authentication.domain.usecase.LoginWithEmailUseCase
import com.kevinespinoza.habitapp.authentication.domain.usecase.LoginUseCases
import com.kevinespinoza.habitapp.authentication.domain.usecase.LogoutUseCase
import com.kevinespinoza.habitapp.authentication.domain.usecase.SignupUseCases
import com.kevinespinoza.habitapp.authentication.domain.usecase.SignupWithEmailUseCase
import com.kevinespinoza.habitapp.authentication.domain.usecase.ValidateEmailUseCase
import com.kevinespinoza.habitapp.authentication.domain.usecase.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {
    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(repository: AuthenticationRepository, emailMatcher: EmailMatcher): LoginUseCases{
        return LoginUseCases(
            LoginWithEmailUseCase(repository),
            ValidateEmailUseCase(emailMatcher),
            ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideSignupUseCases(repository: AuthenticationRepository, emailMatcher: EmailMatcher): SignupUseCases{
        return SignupUseCases(
            SignupWithEmailUseCase(repository),
            ValidateEmailUseCase(emailMatcher),
            ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(repository: AuthenticationRepository): GetUserIdUseCase {
        return GetUserIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthenticationRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }
}