package com.silva021.tanalista.ui.screen.shopping.add.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.usecase.AddShoppingListUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListByIdUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingListUseCase
import com.silva021.tanalista.ui.screen.shopping.add.item.AddShoppingItemUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AddShoppingListViewModel(
    private val addListUseCase: AddShoppingListUseCase,
    private val getShoppingListById: GetShoppingListByIdUseCase,
    private val updateShoppingList: UpdateShoppingListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddShoppingListUiState>(AddShoppingListUiState.Idle())
    val uiState: StateFlow<AddShoppingListUiState> = _uiState.asStateFlow()

    fun getShoppingList(listId: String) {
        if (listId.isEmpty()) {
            _uiState.value = AddShoppingListUiState.Idle()
            return
        }

        viewModelScope.launch {
            _uiState.value = AddShoppingListUiState.Loading
            getShoppingListById.invoke(
                listId,
                onSuccess = {
                    _uiState.value = AddShoppingListUiState.Idle(it)
                },
                onFailure = {
                    _uiState.value = AddShoppingListUiState.Error("Não foi possível carregar a lista")
                }
            )
        }
    }

    fun editShoppingList(list: ShoppingList) {
        viewModelScope.launch {
            _uiState.value = AddShoppingListUiState.Loading
                updateShoppingList.invoke(
                    list,
                    onSuccess = {
                        _uiState.value = AddShoppingListUiState.Success(isUpdated = true)
                    },
                    onFailure = {
                        _uiState.value = AddShoppingListUiState.Error("Não foi possível atualizar a lista")
                    }
                )
        }
    }

    fun addShoppingList(list: ShoppingList) {
        viewModelScope.launch {
            _uiState.value = AddShoppingListUiState.Loading
            addListUseCase.invoke(
                list = list,
                onSuccess = {
                    _uiState.value = AddShoppingListUiState.Success(isUpdated = false)
                },
                onFailure = {
                    _uiState.value = AddShoppingListUiState.Error("Não foi possível criar a lista")
                }
            )
        }
    }
}

sealed class AddShoppingListUiState {
    data class Idle(val shoppingList: ShoppingList? = null) : AddShoppingListUiState()
    object Loading : AddShoppingListUiState()
    data class Success(val isUpdated: Boolean) : AddShoppingListUiState()
    data class Error(val message: String) : AddShoppingListUiState()
}