package com.silva021.tanalista.domain.mappers

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.silva021.tanalista.data.dto.ShoppingItemDTO
import com.silva021.tanalista.data.dto.ShoppingListDTO
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.model.UnitType

fun ShoppingItemDTO.toModel(): ShoppingItem {
    return ShoppingItem(
        id = id,
        name = name,
        quantity = quantity,
        unitType = UnitType.valueOf(unitType),
        listId = listId,
        minRequired = minRequired
    )
}

fun ShoppingListDTO.toModel(): ShoppingList {
    return ShoppingList(
        id = id,
        name = name,
        type = CategoryType.valueOf(type),
        ownerUID = ownerUID,
        ownerName = ownerName,
        isMine = ownerUID == Firebase.auth.uid,
        participants = participants.toList(),
        lastUpdate = lastUpdate
    )
}
