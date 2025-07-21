package com.silva021.tanalista.ui.screen.shopping.add.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.usecase.AddShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemByIdUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingItemUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddShoppingItemViewModel(
    private val addShoppingItem: AddShoppingItemUseCase,
    private val getShoppingItem: GetShoppingItemByIdUseCase,
    private val updateShoppingItem: UpdateShoppingItemUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddShoppingItemUiState>(AddShoppingItemUiState.Idle())
    val uiState: StateFlow<AddShoppingItemUiState> = _uiState

    fun getShoppingItemById(listId: String, itemId: String) {
        if (itemId.isEmpty()) {
            _uiState.value = AddShoppingItemUiState.Idle()
            return
        }

        viewModelScope.launch {
            _uiState.value = AddShoppingItemUiState.Loading
            getShoppingItem.invoke(
                listId = listId,
                itemId = itemId
            ).onSuccess { shoppingItem ->
                _uiState.value = AddShoppingItemUiState.Idle(shoppingItem)
            }.onFailure {
                _uiState.value = AddShoppingItemUiState.Error("Não foi possível obter o item")
            }
        }
    }

    fun add(item: ShoppingItem) {
        viewModelScope.launch {
            _uiState.value = AddShoppingItemUiState.Loading
            addShoppingItem.invoke(shoppingItem = item)
                .onSuccess {
                _uiState.value = AddShoppingItemUiState.Success(isUpdated = false)

            }.onFailure {
                _uiState.value =
                    AddShoppingItemUiState.Error("Não foi possível adicionar o item à lista")
            }
        }
    }

    fun update(item: ShoppingItem) {
        viewModelScope.launch {
            updateShoppingItem.invoke(
                shoppingItem = item
            ).onSuccess {
                _uiState.value = AddShoppingItemUiState.Success(isUpdated = true)
            }.onFailure {
                _uiState.value =
                    AddShoppingItemUiState.Error("Não foi possível atualizar o item")
            }
        }
    }
}