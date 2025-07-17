package com.silva021.tanalista.domain.model

import java.util.UUID

data class ShoppingList(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: CategoryType,
    val ownerUID: String,
    val ownerName: String,
    val isMine: Boolean,
    val sharedWith: List<String> = emptyList(),
)