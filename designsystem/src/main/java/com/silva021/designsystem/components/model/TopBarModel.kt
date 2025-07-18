package com.silva021.designsystem.components.model

data class TopBarModel(
    val title: String? = null,
    val showBackButton: Boolean = false,
    val onBackClick: () -> Unit = {},
)