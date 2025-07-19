package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.getShoppingItemsCollection
import com.silva021.tanalista.data.dto.ShoppingItemDTO
import com.silva021.tanalista.domain.mappers.toModel
import kotlinx.coroutines.tasks.await

class GetShoppingItemByIdUseCase() {
    suspend operator fun invoke(
        listId: String,
        itemId: String,
    ) = try {
        val query = getShoppingItemsCollection(listId)
            .whereEqualTo("id", itemId)
            .get()
            .await()

        Result.success(
            query.documents.firstNotNullOf {
                it.toObject(ShoppingItemDTO::class.java)?.copy(id = it.id)?.toModel()
            }
        )
    } catch (e: Exception) {
        Firebase.crashlytics.recordException(e)
        e.printStackTrace()
        Result.failure(e)
    }
}