package com.silva021.tanalista.util

import androidx.compose.runtime.Composable
import com.silva021.tanalista.ui.theme.Scaffold

@Composable
fun ThemedScreen(content: @Composable () -> Unit) {
    Scaffold {
        content()
    }
}