package com.kevinespinoza.habitapp.home.domain.home.usecase

import com.kevinespinoza.habitapp.home.domain.models.Habit
import com.kevinespinoza.habitapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime

class GetHabitsForDateUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(date: ZonedDateTime): Flow<List<Habit>> {
        return repository.getAllHabitsForSelectedDate(date).map {
            it.filter { it.frequency.contains(date.dayOfWeek) }
        }.distinctUntilChanged()
    }
}