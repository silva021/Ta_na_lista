package com.silva021.tanalista.ui.screen.shopping.sharelist

import androidx.compose.runtime.Composable

@Composable
fun ShareListScreen(
    listId: String,
    onBackPressed: () -> Unit,
) {
    ShareListContent(
        listId = listId,
        onBackPressed = onBackPressed
    )
}
