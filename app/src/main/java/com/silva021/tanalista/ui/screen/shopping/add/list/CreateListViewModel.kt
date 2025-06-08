package com.silva021.tanalista.ui.screen.shopping.add.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.usecase.AddShoppingListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateListViewModel(
    private val addListUseCase: AddShoppingListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CreateListUiState>(CreateListUiState.Idle)
    val uiState: StateFlow<CreateListUiState> = _uiState.asStateFlow()

    fun addShoppingList(list: ShoppingList) {
        viewModelScope.launch {
            _uiState.value = CreateListUiState.Loading
            try {
                addListUseCase.invoke(list)
                _uiState.value = CreateListUiState.Success()
            } catch (e: Exception) {
                _uiState.value = CreateListUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}

sealed class CreateListUiState {
    object Idle : CreateListUiState()
    object Loading : CreateListUiState()
    data class Success(val message: String = "Lista criada com sucesso") : CreateListUiState()
    data class Error(val message: String) : CreateListUiState()
}