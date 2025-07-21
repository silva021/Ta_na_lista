package com.silva021.tanalista.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.silva021.tanalista.data.datastore.FireStoreHelper
import com.silva021.tanalista.data.dto.ShoppingItemDTO
import com.silva021.tanalista.data.dto.ShoppingListDTO
import com.silva021.tanalista.domain.mappers.toModel
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

class GetShoppingListsUseCase() {
    suspend operator fun invoke(): Result<List<ShoppingList>> = coroutineScope {
        try {
            val uid = FirebaseAuth.getInstance().currentUser?.uid
                ?: throw Exception("Usuário não autenticado")

            val listsSnapshot = FireStoreHelper.shoppingListCollection
                .whereArrayContains("participants", uid)
                .get()
                .await()

            val listsWithItems = listsSnapshot.toShoppingListModels().map { list ->
                async {
                    val itemsSnapshot = FireStoreHelper.getShoppingItemsCollection(list.id)
                        .get()
                        .await()

                    val items = itemsSnapshot.documents.mapNotNull {
                        it.toObject(ShoppingItemDTO::class.java)?.toModel()
                    }

                    list.copy(items = items)
                }
            }.awaitAll()

            return@coroutineScope Result.success(listsWithItems)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            return@coroutineScope Result.failure(e)
        }
    }
}

private fun QuerySnapshot.toShoppingListModels(): List<ShoppingList> {
    return documents.mapNotNull {
        it.toObject(ShoppingListDTO::class.java)?.toModel()
    }
}