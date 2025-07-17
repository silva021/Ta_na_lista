package com.silva021.tanalista.data.dto

data class ShoppingItemDTO(
    val id: String = "",
    val listId: String = "",
    val name: String = "",
    val quantity: Float = 0f,
    val minRequired: Float = 0f,
    val unitType: String = "UNIT",
)