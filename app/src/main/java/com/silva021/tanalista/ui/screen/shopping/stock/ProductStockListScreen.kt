package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.domain.model.ShoppingItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductStockListScreen(
    viewModel: ProductStockListViewModel = koinViewModel(),
    listId: String,
    onAdd: () -> Unit,
    onEditShoppingItem: (String) -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getShoppingItems(listId)
    }

    when (val state = uiState) {
        is ProductStockListUiState.Error -> {
            Text("Erro: ${state.message}")
        }

        is ProductStockListUiState.Loading -> {
            CircularProgressIndicator()
        }

        is ProductStockListUiState.Success -> {
            ProductStockListContent(
                items = state.lists,
                onAdd = onAdd,
                onAdjustStock = {
                    viewModel.updateShoppingItems(it)
                },
                onEditShoppingItem = onEditShoppingItem,
                onBackPressed = onBackPressed
            )
        }
    }
}

sealed class ProductStockListUiState {
    object Loading : ProductStockListUiState()
    data class Success(val lists: List<ShoppingItem>) : ProductStockListUiState()
    data class Error(val message: String) : ProductStockListUiState()
}