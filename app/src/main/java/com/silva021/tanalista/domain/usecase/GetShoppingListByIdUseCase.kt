package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.getShoppingItemsCollection
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
            val query = getShoppingItemsCollection(listId)
                .whereEqualTo("listId", listId)
                .get()
                .await()

            val shoppingList = query.documents.firstNotNullOf {
                it.toObject(ShoppingListDTO::class.java)?.copy(id = listId)
            }.toModel()

            onSuccess.invoke(shoppingList)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure.invoke()
        }
    }
}