package com.pgolcursos.biblequiz.ui.model

sealed class ResetPasswordState {
    object Success : ResetPasswordState()
    object ShowScreen : ResetPasswordState()
    object Error : ResetPasswordState()
}
