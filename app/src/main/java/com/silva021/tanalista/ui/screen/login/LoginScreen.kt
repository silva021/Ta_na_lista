package com.silva021.tanalista.ui.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.silva021.tanalista.ui.model.LoginScreenState
import com.silva021.tanalista.ui.theme.Scaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    isLoggedListener: () -> Unit,
    navigateToRegisterScreen: () -> Unit,
    navigateToForgotPasswordScreen: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.state.collectAsState()

    Scaffold { _ ->
        when (val newState = state) {
            is LoginScreenState.Success -> {
                LoginContent(
                    state = newState.model,
                    onEmailChange = viewModel::onEmailChange,
                    onPasswordChange = viewModel::onPasswordChange,
                    onLoginClick = {
                        keyboardController?.hide()
                        viewModel.login()
                    },
                    onRegisterClick = navigateToRegisterScreen,
                    onForgotPasswordClick = navigateToForgotPasswordScreen
                )
            }

            is LoginScreenState.IsLogged -> {
                LaunchedEffect(Unit) {
                    isLoggedListener.invoke()
                }
            }
        }
    }
}
