package com.kevinespinoza.habitapp.home.domain.repository

import com.kevinespinoza.habitapp.home.domain.models.Habit
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

interface HomeRepository {
    fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>>
    suspend fun insetHabit(habit: Habit)
    suspend fun getHabitById(id: String): Habit
    suspend fun insertHabits(habits: List<Habit>)
    suspend fun syncHabits()
}