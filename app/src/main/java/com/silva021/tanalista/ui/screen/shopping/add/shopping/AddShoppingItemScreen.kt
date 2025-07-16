package com.silva021.tanalista.ui.screen.shopping.add.shopping

import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.ui.screen.presentation.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddShoppingItemScreen(
    listId: String,
    itemId: String = "",
    viewModel: AddShoppingItemViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getShoppingItemById(itemId)
    }

    when (val state = uiState) {
        is AddItemUiState.Loading -> {
            LoadingScreen(
                "Criando seu item"
            )
        }
        is AddItemUiState.Success -> {
            LaunchedEffect(Unit) {
                onBack()
            }
        }
        is AddItemUiState.Error -> {
            val error = state.message
            Snackbar { Text(error) }
        }
        is AddItemUiState.Idle -> {
            AddShoppingItemContent(
                listId = listId,
                shoppingItem = state.shoppingItem,
                onAddShoppingItem = { item ->
                    viewModel.add(listId, item)
                },
                onEditShoppingItem = { item ->
                    viewModel.update(item)
                },
                onBackPressed = onBack
            )
        }
    }
}

sealed class AddItemUiState {
    data class Idle(val shoppingItem: ShoppingItem? = null) : AddItemUiState()
    object Loading : AddItemUiState()
    object Success : AddItemUiState()
    data class Error(val message: String) : AddItemUiState()
}