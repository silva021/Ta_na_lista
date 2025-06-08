package com.silva021.tanalista.ui.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.ui.model.LoginScreenState
import com.silva021.tanalista.ui.theme.Scaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    isLoggedListener: () -> Unit,
    navigateToRegisterScreen: () -> Unit,
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToMyListsScreen: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    Scaffold { _ ->
        when (val newState = state) {
            is LoginScreenState.Success -> {
                LoginContent(
                    onLoginClick = navigateToMyListsScreen,
                    onRegisterClick = navigateToRegisterScreen,
                    onGoogleLoginClick = {},
                    onForgotPasswordClick = {}
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
