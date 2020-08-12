package com.example.pass.database

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun revertDate(value: Long): Date? {
        return if (value == -1L) {
            null
        } else {
            Date(value)
        }
    }

    @TypeConverter
    fun converterDate(value: Date?): Long {
        return value?.time ?: -1L
    }
}