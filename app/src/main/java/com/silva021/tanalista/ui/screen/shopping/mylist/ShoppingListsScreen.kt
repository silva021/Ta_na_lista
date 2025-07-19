package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.ui.screen.presentation.ErrorScreen
import com.silva021.tanalista.ui.screen.presentation.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShoppingListsScreen(
    viewModel: ShoppingListsViewModel = koinViewModel(),
    onCardClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit,
    onAddClick: () -> Unit,
    onBackPressed: () -> Unit,
    onAccountClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getShoppingLists()
    }

    when (val state = uiState) {
        is MyListsUiState.Error -> {
            ErrorScreen(
                description = state.message,
                onRetry = { onBackPressed.invoke() }
            )
        }

        is MyListsUiState.Loading -> {
            LoadingScreen(
                "Carregando sua lista de compras"
            )
        }

        is MyListsUiState.Success -> {
            ShoppingListsContent(
                lists = state.lists,
                onCardClick = onCardClick,
                onDeleteClick = { viewModel.deleteList(it) },
                onAddClick = onAddClick,
                onEditClick = onEditClick,
                onAccountClick = onAccountClick
            )
        }
    }
}

sealed class MyListsUiState {
    object Loading : MyListsUiState()
    data class Success(val lists: List<ShoppingList>) : MyListsUiState()
    data class Error(val message: String) : MyListsUiState()
}