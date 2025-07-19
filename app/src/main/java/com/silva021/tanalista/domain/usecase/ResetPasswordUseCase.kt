package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import kotlinx.coroutines.tasks.await

class ResetPasswordUseCase {
    suspend fun invoke(email: String) : Result<Unit> {
        return try {
            Firebase.auth.sendPasswordResetEmail(email.trim()).await()
            Firebase.auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}