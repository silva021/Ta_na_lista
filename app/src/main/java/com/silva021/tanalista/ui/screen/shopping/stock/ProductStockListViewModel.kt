package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.usecase.DeleteShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemsUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingItemUseCase
import com.silva021.tanalista.ui.screen.shopping.mylist.MyListsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProductStockListViewModel(
    private val getLists: GetShoppingItemsUseCase,
    private val updateShoppingItem: UpdateShoppingItemUseCase,
    private val deleteShoppingItem: DeleteShoppingItemUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ProductStockListUiState>(ProductStockListUiState.Loading())
    val uiState: StateFlow<ProductStockListUiState> = _uiState

    fun getShoppingItems(listId: String) {
        viewModelScope.launch {
            getLists.invoke(
                listId = listId,
                onSuccess = { list ->
                    _uiState.value = ProductStockListUiState.Success(list)
                },
                onFailure = {
                    _uiState.value = ProductStockListUiState.Error("Não foi possível carregar a lista")
                }
            )
        }
    }

    fun updateShoppingItems(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            _uiState.value = ProductStockListUiState.Loading(
                message = "Atualizando o item ${shoppingItem.name}"
            )
            updateShoppingItem.invoke(
                shoppingItem = shoppingItem,
                onSuccess = { getShoppingItems(shoppingItem.listId) },
                onFailure = {
                    _uiState.value =
                        ProductStockListUiState.Error("Não foi possível atualizar o item")
                }
            )
        }
    }


    fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            _uiState.value = ProductStockListUiState.Loading(
                message = "Excluindo o item ${shoppingItem.name}"
            )
            deleteShoppingItem.invoke(
                shoppingItem = shoppingItem,
                onSuccess = { getShoppingItems(shoppingItem.listId) },
                onFailure = {
                    _uiState.value = ProductStockListUiState.Error("Não foi possível excluir esse item")
                }
            )
        }
    }
}