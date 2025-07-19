package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LogoutUserUseCase {
    fun invoke(): Result<Unit> {
        return try {
            Firebase.auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
