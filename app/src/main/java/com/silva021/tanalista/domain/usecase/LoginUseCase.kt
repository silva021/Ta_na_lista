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
    ): Result<String> {
        return try {
            val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: throw Exception("UID do usuário não encontrado")

            Result.success(uid)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            Result.failure(
                Exception(
                    when (e) {
                        is FirebaseAuthInvalidCredentialsException -> UserException.PASSWORD_INCORRECT.text
                        else -> UserException.ERROR.text
                    }
                )
            )
        }
    }
}

enum class UserException(val text: String) {
    PASSWORD_INCORRECT("Email ou senha incorretos"),
    ERROR("Erro desconhecido, tente novamente")
}
