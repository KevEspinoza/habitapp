package com.kevinespinoza.habitapp.home.data.local.typeconverter

import android.annotation.SuppressLint
import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.util.joinIntoString
import androidx.room.util.splitToIntList
import com.squareup.moshi.Moshi
import java.lang.NumberFormatException

@ProvidedTypeConverter
class HomeTypeConverter(
) {
    @SuppressLint("RestrictedApi")
    @TypeConverter
    fun fromFrequency(days: List<Int>): String {
        return joinIntoString(days) ?: ""
    }

    @SuppressLint("RestrictedApi")
    @TypeConverter
    fun toFrequency(value: String): List<Int> {
        return splitToIntList(value) ?: emptyList()
    }

    @TypeConverter
    fun fromCompletedDays(days: List<Long>): String {
        return joinIntoStringLong(days) ?: ""
    }

    @TypeConverter
    fun toCompletedDays(value: String): List<Long> {
        return splitToLongList(value) ?: emptyList()
    }

    private fun splitToLongList(input: String?): List<Long>? {
        return input?.split(',')?.mapNotNull { item ->
            try {
                item.toLong()
            } catch (ex: NumberFormatException) {
                null
            }
        }
    }

    private fun joinIntoStringLong(input: List<Long>?): String? {
        return input?.joinToString(",")
    }
}