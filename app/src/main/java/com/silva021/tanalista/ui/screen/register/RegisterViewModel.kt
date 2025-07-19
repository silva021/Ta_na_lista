package com.silva021.tanalista.ui.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.usecase.CreateUserUseCase
import com.silva021.tanalista.ui.model.RegisterScreenModel
import com.silva021.tanalista.ui.model.RegisterScreenState
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

    fun register() {
        val model = authScreenModel ?: RegisterScreenModel()
        val email = model.email.trim()
        val name = model.name.trim()
        val password = model.password.trim()

        viewModelScope.launch {
            _state.update {
                RegisterScreenState.Success(
                    model.copy(
                        isLoading = true,
                        errorMessage = null
                    )
                )
            }

            createUser.invoke(
                name,
                email,
                password
            ).onSuccess {
                _state.update {
                    RegisterScreenState.NavigateToMyListScreen
                }
            }.onFailure { messageError ->
                _state.update {
                    RegisterScreenState.Success(
                        model.copy(
                            isLoading = false,
                            errorMessage = messageError.message
                        )
                    )
                }
            }
        }
    }
}