package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.shoppingListCollection
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.tasks.await

class DeleteShoppingListsUseCase() {
    suspend operator fun invoke(
        list: ShoppingList,
        onSuccess: () -> Unit = {},
        onFailure: (Exception) -> Unit,
    ) {
        try {
            shoppingListCollection.document(list.id).delete().await()
            onSuccess.invoke()
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure.invoke(e)
        }
    }
}