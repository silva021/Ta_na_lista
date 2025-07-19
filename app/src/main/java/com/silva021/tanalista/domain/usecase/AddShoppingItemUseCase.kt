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
    ) =
        try {
            FireStoreHelper.getShoppingItemsCollection(listId).document(item.id).set(item).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(e)
        }
}
