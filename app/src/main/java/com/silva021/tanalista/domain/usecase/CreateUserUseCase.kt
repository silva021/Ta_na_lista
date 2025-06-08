package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.crashlytics
import com.pgolcursos.biblequiz.data.model.UserDTO
import com.silva021.tanalista.util.helper.FirestoreHelper.usersCollection
import kotlinx.coroutines.tasks.await

class CreateUserUseCase {
    suspend fun run(
        name: String,
        email: String,
        password: String,
        numberContact: String,
        onSuccess: () -> Unit,
        onFailure: (CreateUserException) -> Unit,
    ) {
        try {
            val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: throw Exception("UID do usuário não encontrado")

            val userDTO = UserDTO(
                name = name,
                userUid = uid,
                email = email,
                numberContact = numberContact
            )

            usersCollection.document(uid).set(userDTO).await()
            onSuccess()
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            e.printStackTrace()
            onFailure(
                when (e) {
                    is FirebaseAuthWeakPasswordException -> CreateUserException.PASSWORD_INCORRECT
                    is FirebaseAuthUserCollisionException -> CreateUserException.USER_EXIST
                    else -> CreateUserException.ERROR
                }
            )
        }
    }
}

enum class CreateUserException(val text: String) {
    USER_EXIST("Esse email já está sendo utilizado"),
    PASSWORD_INCORRECT("A senha é necessária ter no mínimo 6 digitos"),
    ERROR(
        "Erro desconhecido, tente novamente"
    )
}

