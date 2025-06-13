package com.kevinespinoza.habitapp.home.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinespinoza.habitapp.home.domain.detail.usecase.DetailUsesCases
import com.kevinespinoza.habitapp.home.domain.models.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailUsesCases: DetailUsesCases,
) : ViewModel() {
    var state by mutableStateOf(DetailState())
        private set

    init {
        val id = savedStateHandle.get<String>("habitId")
        if (id != null) {
            viewModelScope.launch {
                val habit = detailUsesCases.getHabitByIdUseCase(id)
                state = state.copy(
                    id = habit.id,
                    habitName = habit.name,
                    frequency = habit.frequency,
                    reminder = habit.reminder,
                    startDate = habit.startDate,
                    completedDates = habit.completedDates
                )
            }
        }
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.FrequencyChange -> {
                val frequency = if (state.frequency.contains(event.dayOfWeek)) {
                    state.frequency - event.dayOfWeek
                } else {
                    state.frequency + event.dayOfWeek
                }

                state = state.copy(
                    frequency = frequency
                )
            }

            DetailEvent.HabitSave -> {
                viewModelScope.launch {
                    val habit = Habit(
                        id = state.id ?: UUID.randomUUID().toString(),
                        name = state.habitName,
                        frequency = state.frequency,
                        reminder = state.reminder,
                        startDate = state.startDate,
                        completedDates = state.completedDates
                    )
                    detailUsesCases.insertHabitUseCase(habit)
                }
                state = state.copy(
                    isSaved = true
                )
            }

            is DetailEvent.NameChange -> {
                state = state.copy(
                    habitName = event.name
                )
            }

            is DetailEvent.ReminderChange -> {
                state = state.copy(
                    reminder = event.time
                )
            }
        }
    }
}