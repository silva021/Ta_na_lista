package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper
import com.silva021.tanalista.domain.model.ShoppingItem
import kotlinx.coroutines.tasks.await

class AddShoppingItemUseCase() {
    suspend operator fun invoke(
        listId: String,
        item: ShoppingItem,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            FireStoreHelper.getShoppingItemsCollection(listId).document(item.id).set(item).await()
            onSuccess.invoke()
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure.invoke()
        }
    }
}