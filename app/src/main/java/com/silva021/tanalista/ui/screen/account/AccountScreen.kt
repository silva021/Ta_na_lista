package com.silva021.tanalista.ui.screen.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.ui.screen.presentation.ErrorScreen
import com.silva021.tanalista.ui.screen.presentation.LoadingScreen
import com.silva021.tanalista.ui.screen.presentation.SuccessScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = koinViewModel(),
    onRateApp: () -> Unit,
    onAboutApp: () -> Unit,
    onTermsAndConditions: () -> Unit,
    onPolicyPrivacy: () -> Unit,
    onResetPassword: () -> Unit,
    navigateToLogin: () -> Unit,
    onBackPressed: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is AccountScreenState.Loading -> {
            LoadingScreen(state.text)
        }

        is AccountScreenState.Error -> {
            ErrorScreen(
                state.text,
                onRetry = onBackPressed
            )

        }

        is AccountScreenState.ShowSettingsScreen -> {
            AccountContent(
                onBackPressed = onBackPressed,
                onResetPassword = onResetPassword,
                onDeleteAccount = { viewModel.deleteAccount() },
                onLogout = { viewModel.logout() },
                onAboutApp = onAboutApp,
                onPolicyPrivacy = onPolicyPrivacy,
                onRateApp = onRateApp,
                onTermsAndConditions = onTermsAndConditions
            )
        }

        is AccountScreenState.Success -> {
            SuccessScreen(
                state.text,
                onBackPressed = navigateToLogin
            )
        }
    }
}