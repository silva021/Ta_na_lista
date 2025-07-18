package com.silva021.tanalista.data.datastore

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

object FireStoreHelper {
    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
        .build()

    private val db by lazy {
        FirebaseFirestore.getInstance().apply {
            firestoreSettings = settings
            }
    }

    private fun getCollection(collectionName: String): CollectionReference {
        return db.collection(collectionName)
    }

    val usersCollection: CollectionReference
        get() = getCollection("users")

    val shoppingListCollection: CollectionReference
        get() = getCollection("shopping_lists")

    fun getShoppingItemsCollection(shoppingListId: String) =
        getCollection("shopping_lists")
            .document(shoppingListId)
            .collection("shopping_items")
}