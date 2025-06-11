package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow

class GetShoppingListByIdUseCase(private val repository: ShoppingRepository) {
    operator fun invoke(listId: String): Flow<ShoppingList> = repository.getShoppingListById(listId)
}