package com.silva021.tanalista.ui.screen.shopping.add.shopping

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.work.ReminderScheduler
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddShoppingItemScreen(
    listId: String,
    itemId: String = "",
    viewModel: AddShoppingItemViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var createReminder by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getShoppingItemById(itemId)
    }

    when (val state = uiState) {
        is AddItemUiState.Loading -> CircularProgressIndicator()
        is AddItemUiState.Success -> {
            LaunchedEffect(Unit) {
                onBack()
            }
        }
        is AddItemUiState.Error -> {
            val error = state.message
            Snackbar { Text(error) }
        }
        is AddItemUiState.Idle -> {
            AddShoppingItemContent(
                listId = listId,
                shoppingItem = state.shoppingItem,
                onAddShoppingItem = { item ->
                    viewModel.add(listId, item)
                    if (createReminder) {
                        ReminderScheduler.schedule(
                            context,
                            item.name,
                            java.time.LocalDateTime.now().plusDays(30)
                        )
                    }
                },
                onEditShoppingItem = { item ->
                    viewModel.update(item)
                    if (createReminder) {
                        ReminderScheduler.schedule(
                            context,
                            item.name,
                            java.time.LocalDateTime.now().plusDays(30)
                        )
                    }
                },
                onBackPressed = onBack,
                onReminderChanged = { createReminder = it }
            )
        }
    }
}

sealed class AddItemUiState {
    data class Idle(val shoppingItem: ShoppingItem? = null) : AddItemUiState()
    object Loading : AddItemUiState()
    object Success : AddItemUiState()
    data class Error(val message: String) : AddItemUiState()
}