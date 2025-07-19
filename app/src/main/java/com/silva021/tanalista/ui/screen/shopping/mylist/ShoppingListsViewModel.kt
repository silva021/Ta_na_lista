package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.usecase.DeleteShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppingListsViewModel(
    private val getLists: GetShoppingListsUseCase,
    private val deleteShoppingLists: DeleteShoppingListsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyListsUiState>(MyListsUiState.Loading)
    val uiState: StateFlow<MyListsUiState> = _uiState

    fun getShoppingLists() {
        viewModelScope.launch {
            getLists
                .invoke()
                .onSuccess { list ->
                    _uiState.value = MyListsUiState.Success(list)
                }.onFailure { e ->
                    _uiState.value = MyListsUiState.Error("Não foi possível carregar as listas")
                }
        }
    }

    fun deleteList(list: ShoppingList) {
        viewModelScope.launch {
            _uiState.value = MyListsUiState.Loading
            deleteShoppingLists
                .invoke(list = list)
                .onSuccess { getShoppingLists() }
                .onFailure {
                    _uiState.value = MyListsUiState.Error("Não foi possível excluir a lista")
                }
        }
    }
}