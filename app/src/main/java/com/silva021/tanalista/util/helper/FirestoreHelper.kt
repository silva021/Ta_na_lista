package com.silva021.tanalista.util.helper

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreHelper {
    const val QUESTIONS_REFERENCE = "questions"

    private val db by lazy { FirebaseFirestore.getInstance() }

    private fun getCollection(collectionName: String): CollectionReference {
        return db.collection(collectionName)
    }

    val usersCollection: CollectionReference
        get() = getCollection("users")

    val questionsCollection: CollectionReference
        get() = getCollection(QUESTIONS_REFERENCE)

    val withdrawalsCollection: CollectionReference
        get() = getCollection("payments")

    val stagesCollection: CollectionReference
        get() = getCollection("stages")
}