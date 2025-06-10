package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.domain.model.ShoppingList
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyListsScreen(
    viewModel: MyListsViewModel = koinViewModel(),
    onCardClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit,
    onAddClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is MyListsUiState.Error -> {
            Text("Erro: ${state.message}")
        }

        is MyListsUiState.Loading -> {
            CircularProgressIndicator()
        }

        is MyListsUiState.Success -> {
            MyListsContent(
                lists = state.lists,
                onCardClick = onCardClick,
                onDeleteClick = {
                    viewModel.deleteList(it)
                },
                onAddClick = onAddClick,
                onEditClick = onEditClick
            )
        }
    }
}

sealed class MyListsUiState {
    object Loading : MyListsUiState()
    data class Success(val lists: List<ShoppingList>) : MyListsUiState()
    data class Error(val message: String) : MyListsUiState()
}