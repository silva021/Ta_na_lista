package com.silva021.tanalista.ui.components.model

data class ButtonModel(
    val label: String,
    val onClick: () -> Unit,
    val enabled: Boolean = true,
)
