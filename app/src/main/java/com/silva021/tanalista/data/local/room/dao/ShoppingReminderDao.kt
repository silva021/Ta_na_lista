package com.silva021.tanalista.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.silva021.tanalista.data.local.room.dto.ShoppingReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingReminderDao {
    @Query("SELECT * FROM shopping_reminder")
    fun getAll(): Flow<List<ShoppingReminderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: ShoppingReminderEntity)

    @Update
    suspend fun update(reminder: ShoppingReminderEntity)

    @Delete
    suspend fun delete(reminder: ShoppingReminderEntity)
}
