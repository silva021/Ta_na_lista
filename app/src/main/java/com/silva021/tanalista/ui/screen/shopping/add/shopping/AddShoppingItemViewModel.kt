package com.silva021.tanalista.ui.screen.shopping.add.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.usecase.AddShoppingItemUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddShoppingItemViewModel(
    private val addShoppingItem: AddShoppingItemUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddItemUiState>(AddItemUiState.Idle)
    val uiState: StateFlow<AddItemUiState> = _uiState

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
}