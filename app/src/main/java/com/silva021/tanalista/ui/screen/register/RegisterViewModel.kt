package com.pgolcursos.biblequiz.ui.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.usecase.CreateUserUseCase
import com.pgolcursos.biblequiz.ui.model.RegisterScreenModel
import com.pgolcursos.biblequiz.ui.model.RegisterScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    val createUser: CreateUserUseCase,
) : ViewModel() {
    private val _state =
        MutableStateFlow<RegisterScreenState>(RegisterScreenState.Success(RegisterScreenModel()))
    val state: StateFlow<RegisterScreenState> = _state

    private val authScreenModel: RegisterScreenModel?
        get() = (state.value as? RegisterScreenState.Success)?.model

    fun onEmailChange(newEmail: String) {
        _state.update { currentState ->
            if (currentState is RegisterScreenState.Success) {
                RegisterScreenState.Success(currentState.model.copy(email = newEmail))
            } else {
                RegisterScreenState.Success(RegisterScreenModel(email = newEmail))
            }
        }
    }

    fun onNameChange(value: String) {
        _state.update { currentState ->
            if (currentState is RegisterScreenState.Success) {
                RegisterScreenState.Success(currentState.model.copy(name = value))
            } else {
                RegisterScreenState.Success(RegisterScreenModel(name = value))
            }
        }
    }

    fun onPasswordChange(newPassword: String) {
        _state.update { currentState ->
            if (currentState is RegisterScreenState.Success) {
                RegisterScreenState.Success(currentState.model.copy(password = newPassword))
            } else {
                RegisterScreenState.Success(RegisterScreenModel(password = newPassword))
            }
        }
    }

    fun onPixKeyChange(pixKey: String) {
        _state.update { currentState ->
            if (currentState is RegisterScreenState.Success) {
                RegisterScreenState.Success(currentState.model.copy(numberContact = pixKey))
            } else {
                RegisterScreenState.Success(RegisterScreenModel(numberContact = pixKey))
            }
        }
    }

    fun register() {
        val model = authScreenModel ?: RegisterScreenModel()
        val email = model.email.trim()
        val name = model.name.trim()
        val password = model.password.trim()
        val numberContact = model.numberContact.trim()

        if (
            hasNotInputError(
                name = name,
                email = email,
                password = password,
                numberContact = numberContact
            )
        ) {
            viewModelScope.launch {
                _state.update {
                    RegisterScreenState.Success(
                        model.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    )
                }

                createUser.run(
                    name,
                    email,
                    password,
                    numberContact,
                    onSuccess = {
                        _state.update {
                            RegisterScreenState.NavigateToStageSelectorScreen
                        }

                    },
                    onFailure = { messageError ->
                        _state.update {
                            RegisterScreenState.Success(
                                model.copy(
                                    isLoading = false,
                                    errorMessage = messageError.text
                                )
                            )
                        }
                    }
                )
            }
        }
    }

    private fun hasNotInputError(
        name: String,
        email: String,
        password: String,
        numberContact: String,
    ): Boolean {
        val model = (state.value as? RegisterScreenState.Success)?.model ?: RegisterScreenModel()
        val errorMessage = when {
            email.isEmpty() -> "Por favor, insira seu email."
            name.isEmpty() -> "Por favor, insira seu email."
            password.isEmpty() -> "Por favor, insira sua senha."
            numberContact.isEmpty() -> "Por favor, Insira seu número para o contato"
            email.length < 4 || password.length < 4 -> "Email e senha devem ter pelo menos 4 caracteres."
            numberContact.length != 10 && numberContact.length != 11 -> "Número de telefone inválido. Deve ter 10 ou 11 dígitos."
            else -> null
        }

        _state.update { RegisterScreenState.Success(model.copy(errorMessage = errorMessage)) }

        return errorMessage.orEmpty().isEmpty()
    }
}