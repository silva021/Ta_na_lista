package com.silva021.tanalista.ui.screen.shopping.add.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.silva021.tanalista.R
import com.silva021.tanalista.ui.theme.Scaffold
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateListScreen(
    listId: String = "",
    viewModel: CreateListViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val messageListCreated = stringResource(R.string.msg_list_created)

    LaunchedEffect(Unit) {
        viewModel.getShoppingList(listId)
    }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is CreateListUiState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(message = state.message)
                }
            }
            is CreateListUiState.Success -> {
                scope.launch {
                    snackbarHostState.showSnackbar(message = messageListCreated)
                    onBackPressed()
                }
            }
            else -> {}
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        when (val state = uiState) {
            is CreateListUiState.Idle -> {
                CreateListContent(
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
            is CreateListUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {}
        }
    }
}
