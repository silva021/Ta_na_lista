package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.silva021.tanalista.data.model.UserDTO
import com.silva021.tanalista.data.model.mapFrom
import com.silva021.tanalista.domain.model.User
import com.silva021.tanalista.util.helper.FirestoreHelper

class GetUserUseCase() {
    suspend fun invoke(
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit = {},
    ) {
        FirestoreHelper.usersCollection.document(Firebase.auth.uid.orEmpty()).get()
            .addOnSuccessListener { result ->
                val userDTO = result.toObject(UserDTO::class.java)
                if (userDTO != null) onSuccess(userDTO.mapFrom())
            }.addOnFailureListener { e ->
                onFailure.invoke(e)
            }
    }
}
