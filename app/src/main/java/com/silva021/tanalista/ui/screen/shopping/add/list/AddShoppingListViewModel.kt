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
            getShoppingListById.invoke(listId).catch { e ->
                _uiState.value = AddShoppingListUiState.Error(e.message ?: "Erro desconhecido")
            }.collect {
                _uiState.value = AddShoppingListUiState.Idle(it)

            }
        }
    }

    fun editShoppingList(list: ShoppingList) {
        viewModelScope.launch {
            _uiState.value = AddShoppingListUiState.Loading
                updateShoppingList.invoke(
                    list,
                    onSuccess = {
                        _uiState.value = AddShoppingListUiState.Success("Lista atualizada com sucesso")
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
                    _uiState.value = AddShoppingListUiState.Success("Lista editada com sucesso")
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
    data class Success(val message: String = "Lista criada com sucesso") : AddShoppingListUiState()
    data class Error(val message: String) : AddShoppingListUiState()
}