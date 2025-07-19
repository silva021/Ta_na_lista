package com.silva021.tanalista.ui.screen.forgotpassword

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.theme.Scaffold
import com.silva021.tanalista.ui.model.ResetPasswordState
import com.silva021.tanalista.ui.screen.presentation.ErrorScreen
import com.silva021.tanalista.ui.screen.presentation.SuccessScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = koinViewModel(),
    onNavigateToLogin: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    Scaffold {
        when (state) {
            is ResetPasswordState.Error -> {
                ErrorScreen(
                    description = "Não foi possível enviar o e-mail de recuperação de senha. Verifique se o e-mail está correto e tente novamente.",
                    onRetry = {
                        viewModel.tryAgain()
                    }
                )
            }

            is ResetPasswordState.ShowScreen -> {
                ForgotPasswordContent(
                    onBackPressed = onBackPressed,
                    sendPasswordResetEmail = {
                        viewModel.resetPassword(it)
                    }
                )
            }

            is ResetPasswordState.Success -> {
                SuccessScreen(
                    description = "Um e-mail com instruções para redefinir sua senha foi enviado para sua caixa de entrada.\n\nSiga as instruções para recuperar o acesso à sua conta.",
                    firstButton = ButtonModel(
                        "Voltar para Login",
                        onClick = onNavigateToLogin
                    ),
                )
            }
        }
    }
}