package com.silva021.tanalista.ui.screen.shopping.add.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.silva021.tanalista.ui.screen.presentation.ErrorScreen
import com.silva021.tanalista.ui.screen.presentation.LoadingScreen
import com.silva021.tanalista.ui.screen.presentation.SuccessScreen
import com.silva021.tanalista.ui.theme.Scaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddShoppingListScreen(
    listId: String = "",
    viewModel: AddShoppingListViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getShoppingList(listId)
    }

    Scaffold { padding ->
        when (val state = uiState) {
            is AddShoppingListUiState.Idle -> {
                AddShoppingListContent(
                    shoppingList = state.shoppingList,
                    onCreateClick = {
                        viewModel.addShoppingList(it)
                    },
                    onEditClick = {
                        viewModel.editShoppingList(it)
                    },
                    onBackPressed = onBackPressed
                )
            }

            is AddShoppingListUiState.Loading -> {
                LoadingScreen(
                    "Carregando sua lista"
                )
            }

            is AddShoppingListUiState.Success -> {
                SuccessScreen(
                    subtitle = if (state.isUpdated)
                        "Lista atualizada com sucesso"
                    else
                        "Lista criada com sucesso",
                    onBackPressed = { onBackPressed() }
                )
            }

            is AddShoppingListUiState.Error -> {
                ErrorScreen(
                    description = "Não foi possível terminar essa operação, tente novamente.",
                    onRetry = { onBackPressed() }
                )
            }
        }
    }
}
