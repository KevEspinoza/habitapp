package com.kevinespinoza.habitapp.home.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kevinespinoza.habitapp.home.domain.home.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    private var currentDayJob: Job? = null

    init {
        getHabits()
        viewModelScope.launch {
            homeUseCases.syncHabitUseCase()
        }
    }

    private fun getHabits(){
        currentDayJob?.cancel()
        currentDayJob = viewModelScope.launch {
            homeUseCases.getHabitsForDateUseCase(state.selectedDate).collectLatest {
                state = state.copy(
                    habits = it
                )
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeDate -> {
                state = state.copy(
                    selectedDate = event.date
                )
                getHabits()
            }

            is HomeEvent.CompleteHabit -> {
                viewModelScope.launch {
                    homeUseCases.completeHabitUseCase(
                        event.habit,
                        state.selectedDate
                    )
                }
            }
        }
    }
}