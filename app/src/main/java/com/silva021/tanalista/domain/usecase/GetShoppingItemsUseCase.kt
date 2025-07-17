package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.getShoppingItemsCollection
import com.silva021.tanalista.data.dto.ShoppingItemDTO
import com.silva021.tanalista.domain.mappers.toModel
import com.silva021.tanalista.domain.model.ShoppingItem
import kotlinx.coroutines.tasks.await

class GetShoppingItemsUseCase() {
    suspend operator fun invoke(
        listId: String,
        onSuccess: (List<ShoppingItem>) -> Unit,
        onFailure: () -> Unit,
    ) {
        try {
            val query = getShoppingItemsCollection(listId)
                .get()
                .await()

            val list = query.documents.mapNotNull {
                it.toObject(ShoppingItemDTO::class.java)?.copy(
                    id = it.id
                )?.toModel()
            }
            onSuccess.invoke(list)

        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure.invoke()
        }
    }
}