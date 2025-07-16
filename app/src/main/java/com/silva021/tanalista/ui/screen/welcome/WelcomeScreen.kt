package com.silva021.tanalista.ui.screen.welcome

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = koinViewModel(),
    onStartClick: () -> Unit,
) {
    WelcomeContent(
        onStartClick = {
            onStartClick.invoke()
            viewModel.setFlagShowWelcomeScreen()
        }
    )
}
