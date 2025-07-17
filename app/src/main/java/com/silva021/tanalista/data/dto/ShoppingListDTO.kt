package com.silva021.tanalista.data.dto

data class ShoppingListDTO(
    val id: String = "",
    val name: String = "",
    val type: String = "OTHER",
    val ownerUID: String = "",
    val ownerName: String = "",
    val sharedWith: List<String> = emptyList(),
)