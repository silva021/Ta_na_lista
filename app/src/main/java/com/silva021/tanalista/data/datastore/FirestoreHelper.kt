package com.silva021.tanalista.data.datastore

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object FireStoreHelper {
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val uid = FirebaseAuth.getInstance().currentUser?.uid ?: throw Exception("Usuário não autenticado")

    private fun getCollection(collectionName: String): CollectionReference {
        return db.collection(collectionName)
    }

    val usersCollection: CollectionReference
        get() = getCollection("users")

    val shoppingListCollection: CollectionReference
        get() = getCollection("users")
            .document(uid)
            .collection("shopping_lists")
}