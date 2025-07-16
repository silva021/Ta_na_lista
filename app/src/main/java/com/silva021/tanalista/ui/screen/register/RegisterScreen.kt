package com.silva021.tanalista.ui.screen.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.silva021.tanalista.ui.model.RegisterScreenState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    navigateToMyListScreen: () -> Unit,
    onBackPressed: () -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.state.collectAsState()

    when (val newState = state) {
        is RegisterScreenState.Loading -> {}
        is RegisterScreenState.Success -> {
            RegisterContent(
                model = newState.model,
                onEmailChange = viewModel::onEmailChange,
                onNameChange = viewModel::onNameChange,
                onPasswordChange = viewModel::onPasswordChange,
                onRegisterClick = {
                    keyboardController?.hide()
                    viewModel.register()
                },
                onLoginClick = onBackPressed,
            )
        }

        is RegisterScreenState.NavigateToMyListScreen -> {
            LaunchedEffect(Unit) {
                navigateToMyListScreen.invoke()
            }
        }
    }
}
