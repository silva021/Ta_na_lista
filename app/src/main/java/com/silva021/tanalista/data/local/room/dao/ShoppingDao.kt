package com.silva021.tanalista.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.silva021.tanalista.data.local.room.dto.ShoppingItemEntity
import com.silva021.tanalista.data.local.room.dto.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Transaction
    @Query("SELECT * FROM shopping_list")
    fun getLists(): Flow<List<ShoppingListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: ShoppingListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItemEntity)

    @Delete
    suspend fun deleteList(list: ShoppingListEntity)

    @Query("DELETE FROM shopping_item WHERE listId = :listId")
    suspend fun deleteItemsByListId(listId: String)

    @Transaction
    @Query("SELECT * FROM shopping_item WHERE listId = :listId")
    fun getShoppingItems(listId: String): Flow<List<ShoppingItemEntity>>

    @Update
    suspend fun updateShoppingItem(item: ShoppingItemEntity)

    @Update
    suspend fun updateShoppingList(list: ShoppingListEntity)

    @Transaction
    @Query("SELECT * FROM shopping_item WHERE id = :itemId")
    fun getShoppingItemById(itemId: String): Flow<ShoppingItemEntity>

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE id = :listId")
    fun getShoppingListById(listId: String): Flow<ShoppingListEntity>
}