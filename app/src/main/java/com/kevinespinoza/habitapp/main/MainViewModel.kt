package com.kevinespinoza.habitapp.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinespinoza.habitapp.authentication.domain.usecase.GetUserIdUseCase
import com.kevinespinoza.habitapp.authentication.domain.usecase.LogoutUseCase
import com.kevinespinoza.habitapp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set

    var isLoggedIn by mutableStateOf(getUserIdUseCase() != null)
        private set

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}