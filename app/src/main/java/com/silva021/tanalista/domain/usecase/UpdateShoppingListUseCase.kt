package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.shoppingListCollection
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.tasks.await

class UpdateShoppingListUseCase() {
    suspend operator fun invoke(
        shoppingList: ShoppingList
    ) : Result<Unit> {
        return try {
            val updates = mutableMapOf<String, Any>()
            updates["name"] = shoppingList.name
            updates["type"] = shoppingList.type.name
            updates["lastUpdate"] = System.currentTimeMillis()
            shoppingListCollection.document(shoppingList.id).update(updates).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}