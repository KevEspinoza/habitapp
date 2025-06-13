package com.kevinespinoza.habitapp.home.data.mapper

import com.kevinespinoza.habitapp.home.data.extension.toStartOfDateTimestamp
import com.kevinespinoza.habitapp.home.data.extension.toTimeStamp
import com.kevinespinoza.habitapp.home.data.extension.toZoneDateTime
import com.kevinespinoza.habitapp.home.data.local.entity.HabitEntity
import com.kevinespinoza.habitapp.home.data.local.entity.HabitSyncEntity
import com.kevinespinoza.habitapp.home.data.remote.dto.HabitDto
import com.kevinespinoza.habitapp.home.data.remote.dto.HabitResponse
import com.kevinespinoza.habitapp.home.domain.models.Habit
import java.time.DayOfWeek

fun HabitEntity.toDomain(): Habit {
    return Habit(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map { DayOfWeek.of(it) },
        completedDates = this.completedDates.map { it.toZoneDateTime().toLocalDate() },
        reminder = this.reminder.toZoneDateTime().toLocalTime(),
        startDate = this.startDate.toZoneDateTime(),

        )
}

fun Habit.toEntity(): HabitEntity {
    return HabitEntity(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map { it.value },
        completedDates = this.completedDates.map { it.toZoneDateTime().toTimeStamp() },
        reminder = this.reminder.toZoneDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDateTimestamp(),
    )
}

fun HabitResponse.toDomain(): List<Habit> {
    return this.entries.map {
        val id = it.key
        val dto = it.value
        Habit(
            id = id,
            name = dto.name,
            frequency = dto.frequency.map { DayOfWeek.of(it) },
            completedDates = dto.completedDates?.map { it.toZoneDateTime().toLocalDate() }
                ?: emptyList(),
            reminder = dto.reminder.toZoneDateTime().toLocalTime(),
            startDate = dto.startDate.toZoneDateTime(),
        )
    }
}

fun Habit.toDto(): HabitResponse {
    val dto = HabitDto(
        name = this.name,
        frequency = this.frequency.map { it.value },
        completedDates = this.completedDates.map { it.toZoneDateTime().toTimeStamp() },
        reminder = this.reminder.toZoneDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDateTimestamp()
    )
    return mapOf(id to dto)
}

fun Habit.toSyncEntity(): HabitSyncEntity{
    return HabitSyncEntity(id)
}