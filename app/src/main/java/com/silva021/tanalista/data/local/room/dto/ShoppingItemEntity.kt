package com.silva021.tanalista.data.local.room.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_item",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = ["id"],
            childColumns = ["listId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("listId")]
)
data class ShoppingItemEntity(
    @PrimaryKey val id: String,
    val listId: String,
    val name: String,
    val quantity: Int,
    val unitType: String
)

