package com.silva021.tanalista.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.silva021.tanalista.data.local.room.dto.ShoppingItemEntity
import com.silva021.tanalista.data.local.room.dto.ShoppingListEntity
import com.silva021.tanalista.data.local.room.dto.ShoppingListWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Transaction
    @Query("SELECT * FROM shopping_list")
    fun getAllListsWithItems(): Flow<List<ShoppingListWithItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: ShoppingListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ShoppingItemEntity>)

    @Delete
    suspend fun deleteList(list: ShoppingListEntity)

    @Query("DELETE FROM shopping_item WHERE listId = :listId")
    suspend fun deleteItemsByListId(listId: String)
}