package com.silva021.tanalista.ui.screen.shopping.showinvite

import android.util.Log
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
fun ShowInviteShoppingListScreen(
    viewModel: ShowInviteShoppingListViewModel = koinViewModel(),
    listId: String,
    navigateToHome: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("ShowInviteShoppingListScreen", "Fetching shopping list with ID: $listId")
        viewModel.getShoppingListById(listId)
    }

    Scaffold { padding ->
        when (val state = uiState) {
            is ShowInviteShoppingListUiState.Idle -> {
                ShowInviteShoppingListContent(
                    shoppingList = state.shoppingList,
                    onAccept = viewModel::acceptInvite,
                    onDecline = {},
                    onBackPressed = onBackPressed
                )
            }

            is ShowInviteShoppingListUiState.Loading -> {
                LoadingScreen(
                    "Procurando seu convite para a lista de compras",
                )
            }

            is ShowInviteShoppingListUiState.Success -> {
                SuccessScreen(
                    description = "Convite aceito com sucesso",
                    onBackPressed = { navigateToHome() }
                )
            }

            is ShowInviteShoppingListUiState.Error -> {
                ErrorScreen(
                    description = "Não foi possível terminar essa operação, tente novamente.",
                    onRetry = { onBackPressed() }
                )
            }
        }
    }
}