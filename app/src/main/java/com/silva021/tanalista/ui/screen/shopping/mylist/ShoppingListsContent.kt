package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.components.SubTitle
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.extension.ThemedScreen
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Scaffold
import com.silva021.designsystem.theme.topBarDefaultColors
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListsContent(
    lists: List<ShoppingList>,
    onCardClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit,
    onDeleteClick: (ShoppingList) -> Unit,
    onAccountClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = Palette.Green,
                contentColor = Color.White
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(R.string.action_add),
                    tint = Color.White
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Title(text = stringResource(R.string.title_my_lists))
                },
                colors = topBarDefaultColors(),
                actions = {
                    IconButton(onClick = onAccountClick) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = stringResource(R.string.action_add)
                        )
                    }
                }
            )
        },
    ) {

        if (lists.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubTitle(
                    stringResource(R.string.text_no_lists),
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            LazyColumn {
                items(lists) { list ->
                    SwipeableListCard(
                        shoppingList = list,
                        onCardClick = { onCardClick(list) },
                        onEditClick = { onEditClick(list) },
                        onDeleteClick = { onDeleteClick(list) },
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMyListsContent() {
    ThemedScreen {
        val shoppingLists = listOf(
            ShoppingList(
                name = "Mercado",
                type = CategoryType.GROCERY,
                ownerUID = "1234567890",
                ownerName = "João Silva",
                isMine = true,
                participants = listOf()
            ),
            ShoppingList(
                name = "Farmácia",
                type = CategoryType.PHARMACY,
                ownerUID = "1234567890",
                ownerName = "João Silva",
                isMine = false,
                participants = listOf()
            )
        )

        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            ShoppingListsContent(
                lists = shoppingLists,
                onCardClick = {},
                onDeleteClick = { /* TODO */ },
                onAddClick = { /* TODO */ },
                onEditClick = {},
                onAccountClick = { /* TODO */ }
            )
        }
    }
}

fun Modifier.setImageSizeDefault() = this.size(DpSize(24.dp, 24.dp))
