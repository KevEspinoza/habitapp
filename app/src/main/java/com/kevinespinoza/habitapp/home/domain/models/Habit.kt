package com.kevinespinoza.habitapp.home.domain.models

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

data class Habit(
    val name: String,
    val frequency: List<DayOfWeek>,
    val completedDates: List<LocalDate>,
    val reminder: LocalTime,
    val id: String,
    val startDate: ZonedDateTime,
)
