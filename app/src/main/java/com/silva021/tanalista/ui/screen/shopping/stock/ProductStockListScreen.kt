package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.util.ThemedScreen
import com.silva021.tanalista.domain.model.StockStatus
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.ui.screen.shopping.mylist.MyListsContent
import com.silva021.tanalista.ui.screen.shopping.mylist.MyListsUiState
import com.silva021.tanalista.ui.theme.Palette
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductStockListScreen(
    viewModel: ProductStockListViewModel = koinViewModel(),
    listId: String,
    onAdd: () -> Unit,
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
                onEditClick = {
                    viewModel.updateShoppingItems(it)
                },
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