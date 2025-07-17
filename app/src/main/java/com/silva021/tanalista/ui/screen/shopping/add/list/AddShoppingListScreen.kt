package com.silva021.tanalista.ui.screen.shopping.add.list

import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.silva021.tanalista.R
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
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.getShoppingList(listId)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
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
                    subtitle = stringResource(R.string.list_created_title),
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
