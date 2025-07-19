package com.silva021.tanalista.ui.screen.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.silva021.tanalista.domain.usecase.DeleteUserAccountUseCase
import com.silva021.tanalista.domain.usecase.LogoutUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccountViewModel(
    private val logout: LogoutUserUseCase,
    private val deleteAccount: DeleteUserAccountUseCase,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<AccountScreenState>(AccountScreenState.ShowSettingsScreen)
    val uiState: StateFlow<AccountScreenState> = _uiState

    fun logout() {
        _uiState.value = AccountScreenState.Loading(text = "Saindo da sua conta...")
        logout.invoke(
            onSuccess = {
                _uiState.value = AccountScreenState.Success(
                    text = "Você saiu da sua conta com sucesso."
                )
            },
            onFailure = {
                _uiState.value = AccountScreenState.Error(
                    text = "Não foi possível sair da sua conta. Tente novamente."
                )
            }
        )
    }

    fun deleteAccount() {
        _uiState.value = AccountScreenState.Loading(text = "Excluindo sua conta...")
        viewModelScope.launch {
            deleteAccount.invoke(
                onSuccess = {
                    _uiState.value = AccountScreenState.Success(
                        text = "Sua conta foi excluída com sucesso."
                    )
                },
                onFailure = {
                    _uiState.value = AccountScreenState.Error(
                        text = when (it) {
                            is FirebaseAuthRecentLoginRequiredException -> "Para a excluir sua conta, você precisa sair e fazer login novamente."
                            else -> "Não foi possível excluir sua conta. Tente novamente."
                        }
                    )
                }
            )
        }
    }

}

sealed class AccountScreenState {
    data class Loading(val text: String) : AccountScreenState()
    data class Success(val text: String) : AccountScreenState()
    data class Error(val text: String) : AccountScreenState()
    object ShowSettingsScreen : AccountScreenState()
}
