package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.usecase.DeleteShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemsUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingItemUseCase
import com.silva021.tanalista.ui.screen.shopping.mylist.MyListsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProductStockListViewModel(
    private val getLists: GetShoppingItemsUseCase,
    private val updateShoppingItem: UpdateShoppingItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductStockListUiState>(ProductStockListUiState.Loading)
    val uiState: StateFlow<ProductStockListUiState> = _uiState

    fun getShoppingItems(listId: String) {
        viewModelScope.launch {
            getLists(listId)
                .catch { e ->
                    _uiState.value = ProductStockListUiState.Error(e.message.toString())
                }
                .collect { list ->
                    _uiState.value = ProductStockListUiState.Success(list)
                }
        }
    }

    fun updateShoppingItems(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            try {
                updateShoppingItem(shoppingItem)
                getShoppingItems(shoppingItem.listId.orEmpty())
            } catch (e: Exception) {
                _uiState.value = ProductStockListUiState.Error(e.message.toString())
            }
        }
    }
}