package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper
import com.silva021.tanalista.domain.model.ShoppingItem
import kotlinx.coroutines.tasks.await

class AddShoppingItemUseCase(
    private val updateLastUpdateInShoppingList: UpdateLastUpdateInShoppingListUseCase,
) {
    suspend operator fun invoke(shoppingItem: ShoppingItem) = try {
        FireStoreHelper.getShoppingItemsCollection(shoppingItem.listId).document(shoppingItem.id)
            .set(shoppingItem).await()
        updateLastUpdateInShoppingList.invoke(shoppingItem.listId)
        Result.success(Unit)
    } catch (e: Exception) {
        Firebase.crashlytics.recordException(e)
        e.printStackTrace()
        Result.failure(e)
    }
}
