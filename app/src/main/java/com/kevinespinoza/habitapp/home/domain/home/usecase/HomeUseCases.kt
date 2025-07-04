package com.kevinespinoza.habitapp.home.domain.home.usecase

data class HomeUseCases(
    val completeHabitUseCase: CompleteHabitUseCase,
    val getHabitsForDateUseCase: GetHabitsForDateUseCase,
    val syncHabitUseCase: SyncHabitUseCase,
)
