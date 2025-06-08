package com.silva021.tanalista.data.repository

import com.silva021.tanalista.data.local.room.dao.ShoppingListDao
import com.silva021.tanalista.domain.mappers.toEntity
import com.silva021.tanalista.domain.mappers.toModel
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingRepositoryImpl(
    private val dao: ShoppingListDao
) : ShoppingRepository {

    override fun getAllLists(): Flow<List<ShoppingList>> =
        dao.getAllListsWithItems().map { it.map { it.toModel() } }

    override suspend fun addList(list: ShoppingList) {
        dao.insertList(list.toEntity())
        dao.insertItems(list.items.map { it.toEntity(list.id) })
    }

    override suspend fun deleteList(list: ShoppingList) {
        dao.deleteItemsByListId(list.id)
        dao.deleteList(list.toEntity())
    }
}

interface ShoppingRepository {
    fun getAllLists(): Flow<List<ShoppingList>>
    suspend fun addList(list: ShoppingList)
    suspend fun deleteList(list: ShoppingList)
}