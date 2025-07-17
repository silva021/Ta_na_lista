package com.silva021.tanalista.domain.mappers

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

fun ShoppingList.toEntity(): ShoppingListDTO {
    return ShoppingListDTO(
        id = id,
        name = name,
        type = type.name
    )
}

fun ShoppingListDTO.toModel(): ShoppingList {
    return ShoppingList(
        id = id,
        name = name,
        type = CategoryType.valueOf(type)
    )
}

fun ShoppingItem.toEntity(listId: String? = null): ShoppingItemDTO {
    return ShoppingItemDTO(
        id = id,
        listId = listId ?: this.listId.orEmpty(),
        name = name,
        quantity = quantity,
        minRequired = minRequired,
        unitType = unitType.name,
    )
}