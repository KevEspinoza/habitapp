package com.kevinespinoza.habitapp.home.domain.detail.usecase

import com.kevinespinoza.habitapp.home.domain.models.Habit
import com.kevinespinoza.habitapp.home.domain.repository.HomeRepository
import java.time.ZonedDateTime

class InsertHabitUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(habit: Habit) {
        repository.insetHabit(habit)
    }
}