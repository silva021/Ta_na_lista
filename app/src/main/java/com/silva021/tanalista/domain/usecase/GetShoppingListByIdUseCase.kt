package com.silva021.tanalista.domain.usecase

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.shoppingListCollection
import com.silva021.tanalista.data.dto.ShoppingListDTO
import com.silva021.tanalista.domain.mappers.toModel
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.tasks.await

class GetShoppingListByIdUseCase() {
    suspend operator fun invoke(
        listId: String
    ) : Result<ShoppingList> {
        return try {
            val query = shoppingListCollection
                .document(listId)
                .get()
                .await()

            val shoppingList = query.toObject(ShoppingListDTO::class.java)?.toModel() ?:
                throw Exception("Shopping list not found for ID: $listId")
            Result.success(shoppingList)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}