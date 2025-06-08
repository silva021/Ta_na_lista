package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow

class DeleteShoppingListsUseCase(private val repository: ShoppingRepository) {
    suspend operator fun invoke(list: ShoppingList): Unit = repository.deleteList(list)
}