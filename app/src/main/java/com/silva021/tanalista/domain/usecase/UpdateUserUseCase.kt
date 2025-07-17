package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper

class UpdateUserUseCase {
    private val usersCollection = FireStoreHelper.usersCollection

    fun invoke(
        newBalance: Float? = null,
        newCurrentStage: Int? = null,
        pixKey: String? = null,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    ) {
        try {
            val updates = mutableMapOf<String, Any>()

            newBalance?.let {
                updates["balance"] = newBalance
            }

            newCurrentStage?.let {
                updates["currentStage"] = newCurrentStage
            }

            pixKey?.let {
                updates["pixKey"] = pixKey
            }

            if (updates.isNotEmpty()) {
                val uid = Firebase.auth.uid.orEmpty()
                usersCollection.document(uid).update(updates).addOnSuccessListener {
                    onSuccess.invoke()
                }.addOnFailureListener {
                    onFailure.invoke()
                }
            } else {
                onSuccess.invoke()
            }
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            onFailure.invoke()
            e.printStackTrace()
        }
    }
}
