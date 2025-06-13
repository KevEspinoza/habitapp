package com.kevinespinoza.habitapp.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HabitEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val frequency: List<Int>,
    val completedDates: List<Long>,
    val reminder: Long,
    val id: String,
    val startDate: Long,
)
