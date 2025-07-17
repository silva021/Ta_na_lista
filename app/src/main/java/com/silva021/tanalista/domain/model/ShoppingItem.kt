package com.silva021.tanalista.domain.model

import java.util.UUID

data class ShoppingItem(
    val id: String = UUID.randomUUID().toString(),
    val listId: String,
    val name: String,
    val quantity: Float,
    val minRequired: Float,
    val unitType: UnitType
)