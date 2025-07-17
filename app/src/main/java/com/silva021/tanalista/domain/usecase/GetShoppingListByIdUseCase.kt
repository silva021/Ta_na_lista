package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.getShoppingItemsCollection
import com.silva021.tanalista.data.datastore.FireStoreHelper.shoppingListCollection
import com.silva021.tanalista.data.dto.ShoppingListDTO
import com.silva021.tanalista.domain.mappers.toModel
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.tasks.await

class GetShoppingListByIdUseCase() {
    suspend operator fun invoke(
        listId: String,
        onSuccess: (ShoppingList) -> Unit,
        onFailure: () -> Unit,
    ) {
        try {
            val query = shoppingListCollection
                .document(listId)
                .get()
                .await()

            query.toObject(ShoppingListDTO::class.java)?.copy(id = listId)?.toModel()?.let {
                onSuccess.invoke(it)
            } ?: run {
                throw Exception("Shopping list not found")
            }
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure.invoke()
        }
    }
}