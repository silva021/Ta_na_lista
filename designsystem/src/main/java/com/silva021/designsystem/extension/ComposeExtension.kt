package com.silva021.designsystem.extension

import androidx.compose.runtime.Composable
import com.silva021.designsystem.theme.Scaffold

@Composable
fun ThemedScreen(content: @Composable () -> Unit) {
    Scaffold {
        content()
    }
}