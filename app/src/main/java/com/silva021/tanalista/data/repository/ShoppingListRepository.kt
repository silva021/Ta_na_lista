package com.silva021.tanalista.data.repository

import com.silva021.tanalista.data.local.room.dao.ShoppingListDao
import com.silva021.tanalista.domain.mappers.toEntity
import com.silva021.tanalista.domain.mappers.toModel
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingRepositoryImpl(
    private val dao: ShoppingListDao
) : ShoppingRepository {

    override fun getAllItems(listId: String): Flow<List<ShoppingItem>> =
        dao.getShoppingItems(listId).map { items ->
            items.map { it.toModel() }
        }
    override suspend fun deleteShoppingItem(shoppingItemId: String) {
        dao.deleteItemsByListId(shoppingItemId)
    }

    override suspend fun addShoppingItem(listId: String, item: ShoppingItem) {
        dao.insertItem(item.toEntity(listId))
    }

    override suspend fun updateShoppingItem(item: ShoppingItem) {
        dao.updateShoppingItem(item.toEntity())
    }

    override fun getShoppingItemById(itemId: String): Flow<ShoppingItem> = dao.getShoppingItemById(itemId)
        .map { it.toModel() }

    override fun getShoppingListById(listId: String): Flow<ShoppingList> = dao.getShoppingListById(listId)
        .map { it.toModel() }

}

interface ShoppingRepository {
    fun getAllItems(listId: String): Flow<List<ShoppingItem>>
    fun getShoppingItemById(itemId: String): Flow<ShoppingItem>
    fun getShoppingListById(listId: String): Flow<ShoppingList>
    suspend fun deleteShoppingItem(shoppingItemId: String)
    suspend fun addShoppingItem(listId: String, item: ShoppingItem)
    suspend fun updateShoppingItem(item: ShoppingItem)
}