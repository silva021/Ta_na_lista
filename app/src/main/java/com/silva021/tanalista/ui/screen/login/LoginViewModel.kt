package com.silva021.tanalista.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.usecase.IsUserLoggedInUseCase
import com.silva021.tanalista.domain.usecase.LoginUseCase
import com.silva021.tanalista.ui.model.LoginScreenModel
import com.silva021.tanalista.ui.model.LoginScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val isUserLoggedIn: IsUserLoggedInUseCase,
    private val login: LoginUseCase,
) : ViewModel() {
    private val _state =
        MutableStateFlow<LoginScreenState>(LoginScreenState.Success(LoginScreenModel()))
    val state: StateFlow<LoginScreenState> = _state

    private val authScreenModel: LoginScreenModel?
        get() = (state.value as? LoginScreenState.Success)?.model

    init {
        _state.value = if (isUserLoggedIn.isLogged()) {
            LoginScreenState.IsLogged
        } else {
            LoginScreenState.Success(LoginScreenModel())
        }
    }

    fun onEmailChange(newEmail: String) {
        _state.update { currentState ->
            if (currentState is LoginScreenState.Success) {
                LoginScreenState.Success(currentState.model.copy(email = newEmail))
            } else {
                LoginScreenState.Success(LoginScreenModel(email = newEmail))
            }
        }
    }

    fun onPasswordChange(newPassword: String) {
        _state.update { currentState ->
            if (currentState is LoginScreenState.Success) {
                LoginScreenState.Success(currentState.model.copy(password = newPassword))
            } else {
                LoginScreenState.Success(LoginScreenModel(password = newPassword))
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            val model = authScreenModel ?: LoginScreenModel()
            _state.update { LoginScreenState.Success(model.copy(isLoading = true)) }

            val email = model.email.trim()
            val password = model.password.trim()

            login.invoke(
                email,
                password
            ).onSuccess {
                _state.update {
                    LoginScreenState.IsLogged
                }
            }.onFailure { userException ->
                _state.update {
                    LoginScreenState.Success(
                        model.copy(
                            isLoading = false,
                            errorMessage = userException.message
                        )
                    )
                }
            }
        }
    }
}