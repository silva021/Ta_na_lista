package com.silva021.tanalista.domain.mappers

import com.silva021.tanalista.data.local.room.dto.ShoppingItemEntity
import com.silva021.tanalista.data.local.room.dto.ShoppingListEntity
import com.silva021.tanalista.data.local.room.dto.ShoppingListWithItems
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ListColor
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.model.UnitType
import java.util.UUID

fun ShoppingListWithItems.toModel(): ShoppingList {
    return ShoppingList(
        id = list.id,
        name = list.name,
        items = items.map { it.toModel() },
        color = ListColor.valueOf(list.color),
        type = CategoryType.valueOf(list.type)
    )
}

fun ShoppingItemEntity.toModel(): ShoppingItem {
    return ShoppingItem(
        id = id,
        name = name,
        quantity = quantity,
        unitType = UnitType.valueOf(unitType)
    )
}

fun ShoppingList.toEntity(): ShoppingListEntity {
    return ShoppingListEntity(
        id = id,
        name = name,
        color = color.name,
        type = type.name
    )
}

fun ShoppingItem.toEntity(listId: String): ShoppingItemEntity {
    return ShoppingItemEntity(
        id = id,
        listId = listId,
        name = name,
        quantity = quantity,
        unitType = unitType.name
    )
}