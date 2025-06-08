package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import kotlinx.coroutines.tasks.await

class LoginUseCase {
    suspend fun invoke(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onFailure: (UserException) -> Unit,
    ) {
        try {
            val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: throw Exception("UID do usuário não encontrado")

            onSuccess(uid)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure(
                when (e) {
                    is FirebaseAuthInvalidCredentialsException -> UserException.PASSWORD_INCORRECT
                    else -> UserException.ERROR
                }
            )
        }
    }
}

enum class UserException(val text: String) {
    PASSWORD_INCORRECT("Email ou senha incorretos"),
    ERROR("Erro desconhecido, tente novamente")
}
