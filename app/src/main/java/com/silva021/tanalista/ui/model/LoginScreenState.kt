package com.silva021.tanalista.ui.model

sealed class LoginScreenState {
    data class Success(val model: LoginScreenModel) : LoginScreenState()
    object IsLogged : LoginScreenState()
}

data class LoginScreenModel(
    val email: String = "lucasssilva021@gmail.com",
    val password: String = "1234567",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)