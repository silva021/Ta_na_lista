package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LogoutUserUseCase {
    fun invoke(
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            Firebase.auth.signOut()
            onSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            onFailure(e)
        }
    }
}
