package com.silva021.tanalista.ui.model

sealed class RegisterScreenState {
    data class Success(val model: RegisterScreenModel) : RegisterScreenState()
    object NavigateToMyListScreen : RegisterScreenState()
    object Loading : RegisterScreenState()
}

data class RegisterScreenModel(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val numberContact: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)