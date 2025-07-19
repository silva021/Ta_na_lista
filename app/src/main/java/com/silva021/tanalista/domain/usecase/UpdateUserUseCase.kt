package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper
import kotlinx.coroutines.tasks.await

class UpdateUserUseCase {
    private val usersCollection = FireStoreHelper.usersCollection

    suspend fun invoke(
        newBalance: Float? = null,
        newCurrentStage: Int? = null,
        pixKey: String? = null,
    ): Result<Unit> {
        return try {
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
                usersCollection.document(uid).update(updates).await()
                Result.success(Unit)
            } else {
                Result.success(Unit)
            }
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
