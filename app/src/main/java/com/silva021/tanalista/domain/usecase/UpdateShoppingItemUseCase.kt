package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.getShoppingItemsCollection
import com.silva021.tanalista.domain.model.ShoppingItem
import kotlinx.coroutines.tasks.await

class UpdateShoppingItemUseCase() {
    suspend operator fun invoke(
        shoppingItem: ShoppingItem
    ) : Result<Unit> {
        return try {
            val updates = mutableMapOf<String, Any>().apply {
                this["name"] = shoppingItem.name
                this["quantity"] = shoppingItem.quantity
                this["minRequired"] = shoppingItem.minRequired
                this["unitType"] = shoppingItem.unitType.name
            }

            getShoppingItemsCollection(shoppingItem.listId)
                .document(shoppingItem.id)
                .update(updates)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}