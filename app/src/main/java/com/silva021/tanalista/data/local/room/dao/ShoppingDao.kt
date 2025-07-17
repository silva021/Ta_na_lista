package com.silva021.tanalista.data.local.room.dao

import com.silva021.tanalista.data.local.room.dto.ShoppingItemEntity
import com.silva021.tanalista.data.dto.ShoppingListDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ShoppingListDao {

    val mockShoppingItems = listOf(
        ShoppingItemEntity(
            id = "item-001",
            listId = "list-abc",
            name = "Arroz",
            quantity = 5f,
            minRequired = 2f,
            unitType = "kg"
        ),
        ShoppingItemEntity(
            id = "item-002",
            listId = "list-abc",
            name = "Feijão",
            quantity = 3f,
            minRequired = 1f,
            unitType = "kg"
        ),
        ShoppingItemEntity(
            id = "item-003",
            listId = "list-abc",
            name = "Óleo",
            quantity = 1f,
            minRequired = 1f,
            unitType = "L"
        )
    )
    val mockShoppingLists = listOf(
        ShoppingListDTO(
            id = "list-abc",
            name = "Lista da Semana",
            type = "Mercado"
        ),
        ShoppingListDTO(
            id = "list-abc",
            name = "Farmácia",
            type = "Saúde"
        ),
        ShoppingListDTO(
            id = "list-abc",
            name = "Material Escolar",
            type = "Papelaria"
        )
    )

    suspend fun insertItem(item: ShoppingItemEntity) {
        // simula um insert mas não faz nada
    }

    suspend fun deleteItemsByListId(listId: String) {
        // simula um delete mas não faz nada
    }

    fun getShoppingItems(listId: String): Flow<List<ShoppingItemEntity>> {
        return flowOf(emptyList())
    }

    suspend fun updateShoppingItem(item: ShoppingItemEntity) {
        // simula um update mas não faz nada
    }

    fun getShoppingItemById(itemId: String): Flow<ShoppingItemEntity> {
        return flowOf(mockShoppingItems[1])
    }

    fun getShoppingListById(listId: String): Flow<ShoppingListDTO> {
        return flowOf(mockShoppingLists[1])
    }
}