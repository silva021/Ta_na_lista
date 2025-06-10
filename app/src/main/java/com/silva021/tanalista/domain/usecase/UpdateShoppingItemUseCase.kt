package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.domain.model.ShoppingItem

class UpdateShoppingItemUseCase(private val repository: ShoppingRepository) {
    suspend operator fun invoke(shoppingItem: ShoppingItem) = repository.updateShoppingItem(shoppingItem)
}