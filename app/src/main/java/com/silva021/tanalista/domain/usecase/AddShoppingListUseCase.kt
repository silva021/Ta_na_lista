package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.domain.model.ShoppingList

class AddShoppingListUseCase(private val repository: ShoppingRepository) {
    suspend operator fun invoke(list: ShoppingList) = repository.addList(list)
}
