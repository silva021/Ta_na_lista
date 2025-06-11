package com.silva021.tanalista.ui.screen.forgotpassword

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.ui.model.ResetPasswordState
import com.silva021.tanalista.ui.screen.forgotpassword.ForgotPasswordViewModel
import com.silva021.tanalista.ui.theme.Scaffold
import org.koin.androidx.compose.koinViewModel


@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    Scaffold {
        when (state) {
            is ResetPasswordState.Error -> {
//                ErrorScreen(
//                    title = "Falha ao Enviar E-mail",
//                    subtitle = "Não foi possível enviar o e-mail de recuperação de senha. Verifique se o e-mail está correto e tente novamente.",
//                    onRetry = {
//                        viewModel.tryAgain()
//                    }
//                )
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
//                PresentationScreen(
//                    title = "E-mail Enviado!",
//                    subtitle = "Um e-mail com instruções para redefinir sua senha foi enviado para sua caixa de entrada. Siga as instruções para recuperar o acesso à sua conta.",
//                    firstButton = ButtonModel("Voltar para Login", onClick = {
//                        onBackPressed()
//                    }
//                    ),
//                )
            }
        }
    }
}