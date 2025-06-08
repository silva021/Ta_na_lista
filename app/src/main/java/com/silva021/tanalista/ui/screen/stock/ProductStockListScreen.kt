package com.silva021.tanalista.ui.screen.stock

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.util.ThemedScreen
import com.silva021.tanalista.domain.model.StockItem
import com.silva021.tanalista.domain.model.StockStatus
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.ui.theme.Palette

@Composable
fun ProductStockListScreen(
    items: List<StockItem>,
    onAdd: () -> Unit,
    onEditClick: (StockItem) -> Unit,
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
                Icon(Icons.Default.Add, contentDescription = "Adicionar", tint = Color.White)
            }
        },
        topBar = {
            TopAppBar(
                backgroundColor = Palette.backgroundColor,
                elevation = 0.dp,
                title = { Text("Estoque", color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
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

                Text("Item", fontSize = 28.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(items) { item ->
                        ProductStockItem(item, onEditClick)
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
            StockItem(
                id = "arroz",
                name = "Arroz",
                currentQuantity = 2.0f,
                unitType = UnitType.KILOGRAM,
                minRequired = 5.0f,
                status = StockStatus.LOW
            ),
            StockItem(
                id = "feijao",
                name = "Feijão",
                currentQuantity = 1.0f,
                unitType = UnitType.KILOGRAM,
                minRequired = 3.0f,
                status = StockStatus.CRITICAL
            ),
            StockItem(
                id = "leite",
                name = "Leite",
                currentQuantity = 2.0f,
                unitType = UnitType.LITER,
                minRequired = 2.0f,
                status = StockStatus.OK
            ),
            StockItem(
                id = "ovos",
                name = "Ovos",
                currentQuantity = 12.0f,
                unitType = UnitType.UNIT,
                minRequired = 18.0f,
                status = StockStatus.LOW
            ),
            StockItem(
                id = "pao",
                name = "Pão",
                currentQuantity = 1.0f,
                unitType = UnitType.UNIT,
                minRequired = 4.0f,
                status = StockStatus.CRITICAL
            )
        )

        ProductStockListScreen(
            items = stockItems,
            onAdd = {},
            onEditClick = {},
            onBackPressed = {}
        )
    }
}