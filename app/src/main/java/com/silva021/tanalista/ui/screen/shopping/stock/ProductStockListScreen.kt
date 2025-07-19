package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.ui.screen.presentation.ErrorScreen
import com.silva021.tanalista.ui.screen.presentation.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductStockListScreen(
    viewModel: ProductStockListViewModel = koinViewModel(),
    listId: String,
    onAdd: () -> Unit,
    onShareList: (String) -> Unit,
    onEditShoppingItem: (ShoppingItem) -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getShoppingItems(listId)
    }

    when (val state = uiState) {
        is ProductStockListUiState.Error -> {
            ErrorScreen(
                description = "Não foi possível carregar sua lista de itens, tente novamente.",
                onRetry = { viewModel.getShoppingItems(listId) }
            )
        }

        is ProductStockListUiState.Loading -> {
            LoadingScreen(state.message)
        }

        is ProductStockListUiState.Success -> {
            ProductStockListContent(
                items = state.lists,
                onAdd = onAdd,
                onShareList = { onShareList.invoke(listId) },
                onAdjustStock = viewModel::updateShoppingItems,
                onEditShoppingItem = onEditShoppingItem,
                onDeleteShoppingItem = viewModel::deleteShoppingItem,
                onBackPressed = onBackPressed
            )
        }
    }
}

sealed class ProductStockListUiState {
    data class Loading(val message: String = "Carregando sua lista de itens") : ProductStockListUiState()
    data class Success(val lists: List<ShoppingItem>) : ProductStockListUiState()
    data class Error(val message: String) : ProductStockListUiState()
}