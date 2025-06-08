package com.silva021.tanalista.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Scaffold(
    topBar: @Composable () -> Unit = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding(),
            contentColor = Color.Transparent,
            topBar = topBar,
            snackbarHost = snackbarHost
        ) { paddingValues ->
            content(paddingValues)
        }
    }
}
