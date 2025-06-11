package com.silva021.tanalista.ui.model

sealed class ResetPasswordState {
    object Success : ResetPasswordState()
    object ShowScreen : ResetPasswordState()
    object Error : ResetPasswordState()
}
