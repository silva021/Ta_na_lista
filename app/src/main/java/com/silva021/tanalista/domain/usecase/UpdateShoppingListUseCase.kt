package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList

class UpdateShoppingListUseCase(private val repository: ShoppingRepository) {
    suspend operator fun invoke(shoppingList: ShoppingList) = repository.updateShoppingList(shoppingList)
}