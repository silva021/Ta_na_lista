package com.silva021.tanalista.ui.screen.shopping.add.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.usecase.AddShoppingListUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListByIdUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CreateListViewModel(
    private val addListUseCase: AddShoppingListUseCase,
    private val getShoppingListById: GetShoppingListByIdUseCase,
    private val updateShoppingList: UpdateShoppingListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CreateListUiState>(CreateListUiState.Idle())
    val uiState: StateFlow<CreateListUiState> = _uiState.asStateFlow()

    fun getShoppingList(listId: String) {
        if (listId.isEmpty()) {
            _uiState.value = CreateListUiState.Idle()
            return
        }

        viewModelScope.launch {
            _uiState.value = CreateListUiState.Loading
            getShoppingListById.invoke(listId).catch { e ->
                _uiState.value = CreateListUiState.Error(e.message ?: "Erro desconhecido")
            }.collect {
                _uiState.value = CreateListUiState.Idle(it)

            }
        }
    }

    fun editShoppingList(list: ShoppingList) {
        viewModelScope.launch {
            _uiState.value = CreateListUiState.Loading

            try {
                updateShoppingList.invoke(list)
                _uiState.value = CreateListUiState.Success("Lista atualizada com sucesso")
            } catch (e: Exception) {
                _uiState.value = CreateListUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }

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
    data class Idle(val shoppingList: ShoppingList? = null) : CreateListUiState()
    object Loading : CreateListUiState()
    data class Success(val message: String = "Lista criada com sucesso") : CreateListUiState()
    data class Error(val message: String) : CreateListUiState()
}