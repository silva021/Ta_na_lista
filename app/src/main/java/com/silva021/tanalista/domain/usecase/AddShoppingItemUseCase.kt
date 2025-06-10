package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.domain.model.ShoppingItem

class AddShoppingItemUseCase(private val repository: ShoppingRepository) {
    suspend operator fun invoke(listId: String, item: ShoppingItem) {
        repository.addShoppingItem(listId, item)
    }
}