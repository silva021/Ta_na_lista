package com.silva021.tanalista.data.local.room.dto

import com.silva021.tanalista.domain.model.StockStatus

data class ShoppingItemEntity(
    val id: String,
    val listId: String,
    val name: String,
    val quantity: Float,
    val minRequired: Float,
    val unitType: String,
)

