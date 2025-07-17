package com.silva021.tanalista.ui.components.model

import androidx.compose.ui.graphics.Color
import com.silva021.tanalista.ui.theme.Palette.buttonColor

data class ButtonModel(
    val label: String,
    val onClick: () -> Unit,
    val enabled: Boolean = true,
    val isLoading: Boolean = false,
    val backgroundColor: Color = buttonColor,
    val textColor: Color = Color.White
)
