package com.pgolcursos.biblequiz.ui.model

data class TopBarModel(
    val title: String? = null,
    val showBackButton: Boolean = false,
    val onBackClick: () -> Unit = {},
)
