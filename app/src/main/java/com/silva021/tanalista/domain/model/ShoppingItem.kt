package com.silva021.tanalista.domain.model

import java.util.UUID

data class ShoppingItem(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val quantity: Int,
    val unitType: UnitType,
)