package com.silva021.tanalista.ui.model

sealed class LoginScreenState {
    data class Success(val model: LoginScreenModel) : LoginScreenState()
    object IsLogged : LoginScreenState()
}

data class LoginScreenModel(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)