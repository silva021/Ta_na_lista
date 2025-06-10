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

    override fun getAllLists(): Flow<List<ShoppingList>> =
        dao.getLists().map { it.map { it.toModel() } }

    override fun getAllItems(listId: String): Flow<List<ShoppingItem>> =
        dao.getShoppingItems(listId).map { items ->
            items.map { it.toModel() }
        }

    override suspend fun addList(list: ShoppingList) {
        dao.insertList(list.toEntity())
    }

    override suspend fun deleteList(list: ShoppingList) {
        dao.deleteItemsByListId(list.id)
        dao.deleteList(list.toEntity())
    }

    override suspend fun addShoppingItem(listId: String, item: ShoppingItem) {
        dao.insertItem(item.toEntity(listId))
    }

    override suspend fun updateShoppingItem(item: ShoppingItem) {
        dao.updateItem(item.toEntity())
    }
}

interface ShoppingRepository {
    fun getAllLists(): Flow<List<ShoppingList>>
    fun getAllItems(listId: String): Flow<List<ShoppingItem>>
    suspend fun addList(list: ShoppingList)
    suspend fun deleteList(list: ShoppingList)
    suspend fun addShoppingItem(listId: String, item: ShoppingItem)
    suspend fun updateShoppingItem(item: ShoppingItem)
}