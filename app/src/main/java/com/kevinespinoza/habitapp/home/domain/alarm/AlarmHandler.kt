package com.kevinespinoza.habitapp.home.domain.alarm

import com.kevinespinoza.habitapp.home.domain.models.Habit

interface AlarmHandler {
    fun setRecurringAlarm(habit: Habit)
    fun cancel(habit: Habit)
}