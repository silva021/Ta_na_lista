package com.silva021.tanalista.ui.screen.shopping.showinvite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.usecase.AcceptInviteShoppingListUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShowInviteShoppingListViewModel(
    val getShoppingListById: GetShoppingListByIdUseCase,
    val acceptInviteShoppingList: AcceptInviteShoppingListUseCase,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ShowInviteShoppingListUiState>(ShowInviteShoppingListUiState.Loading)
    val uiState: StateFlow<ShowInviteShoppingListUiState> = _uiState.asStateFlow()

    suspend fun getShoppingListById(listId: String) {
        getShoppingListById.invoke(
            listId,
        ).onSuccess {
            _uiState.value = ShowInviteShoppingListUiState.Idle(it)
        }.onFailure {
            _uiState.value =
                ShowInviteShoppingListUiState.Error("Não foi possível carregar a lista")
        }
    }

    fun acceptInvite(shoppingList: ShoppingList) {
        viewModelScope.launch {
            acceptInviteShoppingList.invoke(
                shoppingList
            ).onSuccess {
                _uiState.value = ShowInviteShoppingListUiState.Success
            }.onFailure {
                _uiState.value =
                    ShowInviteShoppingListUiState.Error("Não foi possível aceitar o convite")
            }
        }
    }
}

sealed class ShowInviteShoppingListUiState {
    data class Idle(val shoppingList: ShoppingList) : ShowInviteShoppingListUiState()
    object Loading : ShowInviteShoppingListUiState()
    object Success : ShowInviteShoppingListUiState()
    data class Error(val message: String) : ShowInviteShoppingListUiState()
}