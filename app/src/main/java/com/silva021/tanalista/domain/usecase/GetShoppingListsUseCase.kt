package com.silva021.tanalista.domain.usecase

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.silva021.tanalista.data.datastore.FireStoreHelper
import com.silva021.tanalista.data.dto.ShoppingListDTO
import com.silva021.tanalista.domain.mappers.toModel
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.tasks.await

class GetShoppingListsUseCase() {
    suspend operator fun invoke(
        onSuccess: (List<ShoppingList>) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val shoppingListsQuery = FireStoreHelper.shoppingListCollection.get().await()

            onSuccess.invoke(
                shoppingListsQuery
                    .documents
                    .mapNotNull {
                        it.toObject(ShoppingListDTO::class.java)?.copy(id = it.id)?.toModel()
                    }
                    .asReversed()
            )

        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure.invoke(e)
        }
    }
}