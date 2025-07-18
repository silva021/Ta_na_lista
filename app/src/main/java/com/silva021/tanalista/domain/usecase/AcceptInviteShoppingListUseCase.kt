package com.silva021.tanalista.domain.usecase

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.shoppingListCollection
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.tasks.await

class AcceptInviteShoppingListUseCase() {
    suspend operator fun invoke(
        shoppingList: ShoppingList
    ) : Result<Unit> {
        return try {
            val updates = mutableMapOf<String, Any>()
            val newSharedList = shoppingList.sharedWith.distinct().toMutableList().apply {
                add(Firebase.auth.uid.orEmpty())
            }

            updates["sharedWith"] = newSharedList

            shoppingListCollection.document(shoppingList.id).update(updates).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}