package com.silva021.tanalista.ui.screen.shopping.add.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.usecase.AddShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemByIdUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingItemUseCase
import com.silva021.tanalista.ui.screen.shopping.stock.ProductStockListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AddShoppingItemViewModel(
    private val addShoppingItem: AddShoppingItemUseCase,
    private val getShoppingItem: GetShoppingItemByIdUseCase,
    private val updateShoppingItem: UpdateShoppingItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddItemUiState>(AddItemUiState.Idle())
    val uiState: StateFlow<AddItemUiState> = _uiState

    fun getShoppingItemById(itemId: String) {
        if (itemId.isEmpty()) {
            _uiState.value = AddItemUiState.Idle()
            return
        }

        viewModelScope.launch {
            _uiState.value = AddItemUiState.Loading
            getShoppingItem(itemId)
                .catch { e ->
                    _uiState.value = AddItemUiState.Error(e.message.toString())
                }
                .collect { shoppingItem ->
                    _uiState.value = AddItemUiState.Idle(shoppingItem)
                }
        }
    }

    fun add(listId: String, item: ShoppingItem) {
        viewModelScope.launch {
            _uiState.value = AddItemUiState.Loading
            try {
                addShoppingItem(listId, item)
                _uiState.value = AddItemUiState.Success
            } catch (e: Exception) {
                _uiState.value = AddItemUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }

    fun update(item: ShoppingItem) {
        viewModelScope.launch {
            _uiState.value = AddItemUiState.Loading
            try {
                updateShoppingItem(item)
                _uiState.value = AddItemUiState.Success
            } catch (e: Exception) {
                _uiState.value = AddItemUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}