package com.silva021.tanalista.ui.screen.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.ui.model.RegisterScreenState
import com.silva021.tanalista.ui.screen.register.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    navigateToStageSelectorScreen: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    when (val newState = state) {
        is RegisterScreenState.Loading -> {}
        is RegisterScreenState.Success -> {
            RegisterContent(
                onRegisterClick = { },
                onLoginClick = {},
            )
        }

        is RegisterScreenState.NavigateToStageSelectorScreen -> {
            LaunchedEffect(Unit) {
                navigateToStageSelectorScreen.invoke()
            }
        }
    }
}
