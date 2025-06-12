package com.silva021.tanalista.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.silva021.tanalista.data.local.room.dao.Converters
import com.silva021.tanalista.data.local.room.dao.ShoppingListDao
import com.silva021.tanalista.data.local.room.dao.ShoppingReminderDao
import com.silva021.tanalista.data.local.room.dto.ShoppingItemEntity
import com.silva021.tanalista.data.local.room.dto.ShoppingListEntity
import com.silva021.tanalista.data.local.room.dto.ShoppingReminderEntity

@Database(
    entities = [
        ShoppingListEntity::class,
        ShoppingItemEntity::class,
        ShoppingReminderEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun shoppingReminderDao(): ShoppingReminderDao
}