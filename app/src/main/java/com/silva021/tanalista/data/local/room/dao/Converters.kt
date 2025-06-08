package com.silva021.tanalista.data.local.room.dao

import androidx.room.TypeConverter
import com.silva021.tanalista.domain.model.UnitType

class Converters {
    @TypeConverter
    fun fromUnitType(value: UnitType): String = value.name

    @TypeConverter
    fun toUnitType(value: String): UnitType = UnitType.valueOf(value)
}