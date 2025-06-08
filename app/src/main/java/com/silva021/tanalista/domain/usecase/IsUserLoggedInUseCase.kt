package com.silva021.tanalista.domain.usecase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class IsUserLoggedInUseCase {
    fun isLogged() =  Firebase.auth.currentUser != null
}