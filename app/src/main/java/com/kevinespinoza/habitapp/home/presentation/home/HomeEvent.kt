package com.kevinespinoza.habitapp.home.presentation.home

import com.kevinespinoza.habitapp.home.domain.models.Habit
import java.time.ZonedDateTime

sealed interface HomeEvent {
    data class ChangeDate(val date: ZonedDateTime) : HomeEvent
    data class CompleteHabit(val habit: Habit): HomeEvent
}