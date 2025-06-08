package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import kotlinx.coroutines.tasks.await

class ResetPasswordUseCase {
    suspend fun invoke(
        email: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    ) {
        try {
            Firebase.auth.sendPasswordResetEmail(email).await()
            onSuccess.invoke()
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure()
        }
    }
}