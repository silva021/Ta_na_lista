package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.getShoppingItemsCollection
import com.silva021.tanalista.domain.model.ShoppingItem
import kotlinx.coroutines.tasks.await

class DeleteShoppingItemUseCase() {
    suspend operator fun invoke(
        shoppingItem: ShoppingItem,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    ) {
        try {
            getShoppingItemsCollection(shoppingItem.listId).document(shoppingItem.id).delete().await()
            onSuccess.invoke()
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure.invoke()
        }
    }
}