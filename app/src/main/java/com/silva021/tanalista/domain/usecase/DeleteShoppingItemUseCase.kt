package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow

class DeleteShoppingItemUseCase(private val repository: ShoppingRepository) {
    suspend operator fun invoke(shoppingItem: ShoppingItem): Unit = repository.deleteShoppingItem(shoppingItem.id)
}