package com.silva021.tanalista.ui.screen.shopping.add.shopping

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddShoppingItemScreen(
    listId: String = "",
    viewModel: AddShoppingItemViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is AddItemUiState.Loading -> CircularProgressIndicator()
        is AddItemUiState.Success -> {
            LaunchedEffect(Unit) {
                onBack()
            }
        }
        is AddItemUiState.Error -> {
            val error = (uiState as AddItemUiState.Error).message
            Snackbar { Text(error) }
        }
        is AddItemUiState.Idle -> {
            AddShoppingItemContent(
                onAddShoppingItem = { item ->
                    viewModel.add(listId, item)
                },
                onBackPressed = onBack
            )
        }
    }
}

sealed class AddItemUiState {
    object Idle : AddItemUiState()
    object Loading : AddItemUiState()
    object Success : AddItemUiState()
    data class Error(val message: String) : AddItemUiState()
}