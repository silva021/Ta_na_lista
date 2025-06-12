package com.silva021.tanalista.data.local.room.dao

import androidx.room.TypeConverter
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.domain.model.ReminderType
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class Converters {
    @TypeConverter
    fun fromUnitType(value: UnitType): String = value.name

    @TypeConverter
    fun toUnitType(value: String): UnitType = UnitType.valueOf(value)

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime): Long = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    @TypeConverter
    fun toLocalDateTime(value: Long): LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault())

    @TypeConverter
    fun fromReminderType(value: ReminderType): String = value.name

    @TypeConverter
    fun toReminderType(value: String): ReminderType = ReminderType.valueOf(value)
}