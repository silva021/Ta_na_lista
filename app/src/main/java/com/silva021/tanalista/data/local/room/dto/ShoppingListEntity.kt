package com.silva021.tanalista.data.local.room.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ListColor
import com.silva021.tanalista.domain.model.ShoppingItem

@Entity(tableName = "shopping_list")
data class ShoppingListEntity(
    @PrimaryKey val id: String,
    val name: String,
    val type: String
)