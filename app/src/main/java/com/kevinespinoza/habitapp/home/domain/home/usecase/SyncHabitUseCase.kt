package com.kevinespinoza.habitapp.home.domain.home.usecase

import com.kevinespinoza.habitapp.home.domain.repository.HomeRepository

class SyncHabitUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke() {
        repository.syncHabits()
    }
}