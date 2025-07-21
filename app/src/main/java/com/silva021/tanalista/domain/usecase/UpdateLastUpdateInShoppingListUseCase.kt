package com.silva021.tanalista.domain.usecase

import com.google.android.gms.tasks.Task
import com.silva021.tanalista.data.datastore.FireStoreHelper.shoppingListCollection
import kotlinx.coroutines.tasks.await

class UpdateLastUpdateInShoppingListUseCase() {
    suspend operator fun invoke(
        shoppingListId: String,
    ): Task<Void?> {
        val updates = mutableMapOf<String, Any>()
        updates["lastUpdate"] = System.currentTimeMillis()
        return shoppingListCollection
            .document(shoppingListId)
            .update(updates)
    }
}