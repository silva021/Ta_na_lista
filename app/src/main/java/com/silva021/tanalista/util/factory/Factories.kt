package com.silva021.tanalista.util.factory

import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.model.UnitType
import java.util.UUID

object ShoppingFactory {
    fun createShoppingList(
        name: String = "Nova Lista",
        type: CategoryType = CategoryType.OTHER,
        id: String = UUID.randomUUID().toString()
    ): ShoppingList {
        return ShoppingList(
            id = id,
            name = name,
            type = type
        )
    }

    fun createShoppingItem(
        name: String = "Novo Item",
        quantity: Float = 1f,
        minRequired: Float = 1f,
        unitType: UnitType = UnitType.UNIT,
        listId: String,
        id: String = UUID.randomUUID().toString()
    ): ShoppingItem {
        return ShoppingItem(
            id = id,
            listId = listId,
            name = name,
            quantity = quantity,
            minRequired = minRequired,
            unitType = unitType
        )
    }
}