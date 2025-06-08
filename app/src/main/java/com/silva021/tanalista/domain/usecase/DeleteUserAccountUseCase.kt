package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import com.silva021.tanalista.util.helper.FirestoreHelper.usersCollection
import kotlinx.coroutines.tasks.await

class DeleteUserAccountUseCase {
    suspend fun invoke(
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val user = Firebase.auth.currentUser
            user?.delete()?.await()
            usersCollection.document(user?.uid.orEmpty()).delete()
            onSuccess.invoke()
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure.invoke(e)
        }
    }
}
