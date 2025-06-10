package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.usecase.DeleteShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MyListsViewModel(
    private val getLists: GetShoppingListsUseCase,
    private val deleteShoppingLists: DeleteShoppingListsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyListsUiState>(MyListsUiState.Loading)
    val uiState: StateFlow<MyListsUiState> = _uiState

    init {
        viewModelScope.launch {
            getLists()
                .catch { e ->
                    _uiState.value = MyListsUiState.Error(e.message.toString())
                }
                .collect { list ->
                    _uiState.value = MyListsUiState.Success(list)
                }
        }
    }

    fun deleteList(list: ShoppingList) {
        viewModelScope.launch {
            try {
                deleteShoppingLists(list)
            } catch (e: Exception) {
                _uiState.value = MyListsUiState.Error(e.message.toString())
            }
        }
    }
}