package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.data.datastore.FireStoreHelper.usersCollection
import kotlinx.coroutines.tasks.await

class DeleteUserAccountUseCase {
    suspend fun invoke(
    ) : Result<Unit> {
        return try {
            val user = Firebase.auth.currentUser
            usersCollection.document(user?.uid.orEmpty()).delete().await()
            user?.delete()?.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
