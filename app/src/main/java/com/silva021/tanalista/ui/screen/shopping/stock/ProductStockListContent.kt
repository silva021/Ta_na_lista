package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.designsystem.components.SubTitle
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.extension.ThemedScreen
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Scaffold
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.util.factory.ShoppingFactory

@Composable
fun ProductStockListContent(
    items: List<ShoppingItem>,
    onAdd: () -> Unit,
    onShareList: () -> Unit,
    onEditShoppingItem: (ShoppingItem) -> Unit,
    onDeleteShoppingItem: (ShoppingItem) -> Unit,
    onAdjustStock: (ShoppingItem) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAdd,
                backgroundColor = Palette.buttonColor,
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
                backgroundColor = Palette.backgroundColor,
                elevation = 0.dp,
                title = { Text(stringResource(R.string.title_stock), color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.content_desc_back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onShareList) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = stringResource(R.string.action_add)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column {
            Column(
                modifier = Modifier
                    .background(Palette.backgroundColor)
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                SubTitle(stringResource(R.string.label_item), fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                if (items.isNotEmpty()) {
                    LazyColumn {
                        items(items) { item ->
                            ProductStockItem(
                                item = item,
                                onAdjustStock = onAdjustStock,
                                onEditShoppingItem = onEditShoppingItem,
                                onDeleteShoppingItem = onDeleteShoppingItem
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SubTitle(
                            text = stringResource(R.string.label_no_items),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewShoppingListScreen() {
    ThemedScreen {
        val stockItems = listOf(
            ShoppingFactory.createShoppingItem(
                id = "arroz",
                name = "Arroz",
                quantity = 2f,
                listId = "a",
                unitType = UnitType.KILOGRAM,
            ),
            ShoppingFactory.createShoppingItem(
                id = "feijao",
                name = "Feijão",
                quantity = 1f,
                listId = "a",
                unitType = UnitType.KILOGRAM,
            ),
            ShoppingFactory.createShoppingItem(
                id = "leite",
                name = "Leite",
                quantity = 25f,
                listId = "a",
                unitType = UnitType.LITER,
            ),
        )

        ProductStockListContent(
            items = stockItems,
            onAdd = {},
            onShareList = { /* Handle share */ },
            onAdjustStock = {},
            onBackPressed = {},
            onEditShoppingItem = { /* Handle edit */ },
            onDeleteShoppingItem = { /* Handle delete */ }
        )
    }
}

@Preview
@Composable
fun PreviewEmptyShoppingListScreen() {
    ThemedScreen {
        ProductStockListContent(
            items = emptyList(),
            onAdd = {},
            onShareList = { /* Handle share */ },
            onAdjustStock = {},
            onBackPressed = {},
            onEditShoppingItem = { /* Handle edit */ },
            onDeleteShoppingItem = {}
        )
    }
}