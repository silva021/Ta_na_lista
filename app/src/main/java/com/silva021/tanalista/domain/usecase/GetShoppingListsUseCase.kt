package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow

class GetShoppingListsUseCase(private val repository: ShoppingRepository) {
    operator fun invoke(): Flow<List<ShoppingList>> = repository.getAllLists()
}