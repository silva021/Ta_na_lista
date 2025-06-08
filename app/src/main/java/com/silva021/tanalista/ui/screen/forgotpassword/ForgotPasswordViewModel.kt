package com.pgolcursos.biblequiz.ui.screen.auth.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.usecase.ResetPasswordUseCase
import com.pgolcursos.biblequiz.ui.model.ResetPasswordState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val resetPassword: ResetPasswordUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ResetPasswordState>(ResetPasswordState.ShowScreen)
    val state: StateFlow<ResetPasswordState> = _state

    var email: String = ""
    fun resetPassword(email: String) {
        this@ForgotPasswordViewModel.email = email
        viewModelScope.launch {
            resetPassword.invoke(
                email = email,
                onSuccess = {
                    _state.value = ResetPasswordState.Success
                },
                onFailure = {
                    _state.value = ResetPasswordState.Error
                }
            )
        }
    }

    fun tryAgain() {
        resetPassword(email)
    }
}