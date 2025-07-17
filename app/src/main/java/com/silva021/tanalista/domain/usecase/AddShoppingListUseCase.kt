package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.tasks.await

class AddShoppingListUseCase() {
    suspend operator fun invoke(
        list: ShoppingList,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val shoppingListCollection = FireStoreHelper.shoppingListCollection.document()
            shoppingListCollection.set(list).await()
            onSuccess.invoke()
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure.invoke(e)
        }
    }
}
