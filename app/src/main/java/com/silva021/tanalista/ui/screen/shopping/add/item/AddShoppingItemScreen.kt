package com.silva021.tanalista.ui.screen.shopping.add.item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.ui.screen.presentation.ErrorScreen
import com.silva021.tanalista.ui.screen.presentation.LoadingScreen
import com.silva021.tanalista.ui.screen.presentation.SuccessScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddShoppingItemScreen(
    listId: String,
    itemId: String = "",
    viewModel: AddShoppingItemViewModel = koinViewModel(),
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getShoppingItemById(listId, itemId)
    }

    when (val state = uiState) {
        is AddShoppingItemUiState.Loading -> {
            LoadingScreen("Carregando")
        }
        is AddShoppingItemUiState.Success -> {
            SuccessScreen(
                subtitle = if(state.isUpdated)
                    stringResource(R.string.shopping_item_updated_title)
                else
                    stringResource(R.string.shopping_item_created_title),
                onBackPressed = { onBackPressed() }
            )
        }
        is AddShoppingItemUiState.Error -> {
            ErrorScreen(
                description = "Não foi possível terminar essa operação, tente novamente.",
                onRetry = { onBackPressed() }
            )
        }
        is AddShoppingItemUiState.Idle -> {
            AddShoppingItemContent(
                listId = listId,
                shoppingItem = state.shoppingItem,
                onAddShoppingItem = { item ->
                    viewModel.add(listId, item)
                },
                onEditShoppingItem = { item ->
                    viewModel.update(item)
                },
                onBackPressed = onBackPressed
            )
        }
    }
}

sealed class AddShoppingItemUiState {
    data class Idle(val shoppingItem: ShoppingItem? = null) : AddShoppingItemUiState()
    object Loading : AddShoppingItemUiState()
    data class Success(val isUpdated: Boolean) : AddShoppingItemUiState()
    data class Error(val message: String) : AddShoppingItemUiState()
}