package com.silva021.tanalista.domain.mappers

import com.silva021.tanalista.data.local.room.dto.ShoppingItemEntity
import com.silva021.tanalista.data.local.room.dto.ShoppingListEntity
import com.silva021.tanalista.data.local.room.dto.ShoppingReminderEntity
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.domain.model.ReminderType
import com.silva021.tanalista.domain.model.ShoppingReminder

fun ShoppingItemEntity.toModel(): ShoppingItem {
    return ShoppingItem(
        id = id,
        name = name,
        quantity = quantity,
        unitType = UnitType.valueOf(unitType),
        listId = listId,
        minRequired = minRequired
    )
}

fun ShoppingList.toEntity(): ShoppingListEntity {
    return ShoppingListEntity(
        id = id,
        name = name,
        type = type.name
    )
}

fun ShoppingListEntity.toModel(): ShoppingList {
    return ShoppingList(
        id = id,
        name = name,
        type = CategoryType.valueOf(type)
    )
}

fun ShoppingItem.toEntity(listId: String? = null): ShoppingItemEntity {
    return ShoppingItemEntity(
        id = id,
        listId = listId ?: this.listId.orEmpty(),
        name = name,
        quantity = quantity,
        minRequired = minRequired,
        unitType = unitType.name,
    )
}

fun ShoppingReminderEntity.toModel(): ShoppingReminder {
    return ShoppingReminder(
        id = id,
        itemId = itemId,
        reminderType = ReminderType.valueOf(reminderType),
        nextReminder = java.time.LocalDateTime.ofEpochSecond(nextReminder / 1000, 0, java.time.ZoneOffset.UTC)
    )
}

fun ShoppingReminder.toEntity(): ShoppingReminderEntity {
    return ShoppingReminderEntity(
        id = id,
        itemId = itemId,
        reminderType = reminderType.name,
        nextReminder = nextReminder.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
    )
}