package com.silva021.tanalista.data.local.room.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_reminder",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("itemId")]
)
data class ShoppingReminderEntity(
    @PrimaryKey val id: String,
    val itemId: String,
    val reminderType: String,
    val nextReminder: Long
)
