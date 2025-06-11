package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.util.ThemedScreen
import com.silva021.tanalista.util.factory.ShoppingFactory

@Composable
fun ProductStockListContent(
    items: List<ShoppingItem>,
    onAdd: () -> Unit,
    onEditShoppingItem: (String) -> Unit,
    onAdjustStock: (ShoppingItem) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        backgroundColor = Palette.backgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAdd,
                backgroundColor = Palette.buttonColor,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.action_add), tint = Color.White)
            }
        },
        topBar = {
            TopAppBar(
                backgroundColor = Palette.backgroundColor,
                elevation = 0.dp,
                title = { Text(stringResource(R.string.title_stock), color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.content_desc_back))
                    }
                })
        }
    ) { _ ->
        Column {
            Column(
                modifier = Modifier
                    .background(Palette.backgroundColor)
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(stringResource(R.string.label_item), fontSize = 28.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(items) { item ->
                        ProductStockItem(
                            item = item,
                            onAdjustStock = onAdjustStock,
                            onEditShoppingItem = onEditShoppingItem
                        )
                        Spacer(modifier = Modifier.height(16.dp))
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
                unitType = UnitType.KILOGRAM,
            ),
            ShoppingFactory.createShoppingItem(
                id = "feijao",
                name = "Feijão",
                quantity = 1f,
                unitType = UnitType.KILOGRAM,
            ),
            ShoppingFactory.createShoppingItem(
                id = "leite",
                name = "Leite",
                quantity = 25f,
                unitType = UnitType.LITER,
            ),
        )

        ProductStockListContent(
            items = stockItems,
            onAdd = {},
            onAdjustStock = {},
            onBackPressed = {},
            onEditShoppingItem = { /* Handle edit */ }
        )
    }
}