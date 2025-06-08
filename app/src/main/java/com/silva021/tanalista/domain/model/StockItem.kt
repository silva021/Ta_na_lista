package com.silva021.tanalista.domain.model

data class StockItem(
    val id: String,
    val name: String,
    val currentQuantity: Float,
    val unitType: UnitType,
    val minRequired: Float,
    val status: StockStatus
)