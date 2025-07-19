package com.silva021.tanalista.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.silva021.tanalista.data.datastore.FireStoreHelper
import com.silva021.tanalista.data.dto.ShoppingListDTO
import com.silva021.tanalista.domain.mappers.toModel
import com.silva021.tanalista.domain.model.ShoppingList
import kotlinx.coroutines.tasks.await

class GetShoppingListsUseCase() {
    suspend operator fun invoke() : Result<List<ShoppingList>> {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: throw Exception("Usuário não autenticado")
        return try {
            val ownerListsQuery = FireStoreHelper.shoppingListCollection
                .whereEqualTo("ownerUID", uid)
                .get()
                .await()

            val sharedListsQuery = FireStoreHelper.shoppingListCollection
                .whereArrayContains("sharedWith", uid)
                .get()
                .await()

            val ownerLists = ownerListsQuery.documents.mapNotNull {
                it.toObject(ShoppingListDTO::class.java)
            }

            val sharedLists = sharedListsQuery.documents.mapNotNull {
                it.toObject(ShoppingListDTO::class.java)
            }

            val combinedLists = (ownerLists + sharedLists).distinctBy { it.id }.map { it.toModel() }

            Result.success(combinedLists)

        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}