package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ListColor
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.model.StockStatus
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.ui.components.ListCard
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.util.ThemedScreen

@Composable
fun MyListsContent(
    lists: List<ShoppingList>,
    onCardClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit,
    onDeleteClick: (ShoppingList) -> Unit,
    onAddClick: () -> Unit,
) {
    Scaffold(
        backgroundColor = Palette.backgroundColor, floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                backgroundColor = Palette.buttonColor,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar", tint = Color.White)
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Minhas Listas",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2B2B2B)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (lists.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Você ainda não tem nenhuma lista", fontWeight = FontWeight.Bold)
                }
            } else {
                lists.forEach { list ->
                    ListCard(
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
            ShoppingList(name = "Mercado", type = CategoryType.GROCERY),
            ShoppingList(name = "Farmácia", type = CategoryType.PHARMACY)
        )

        MyListsContent(
            lists = shoppingLists,
            onCardClick = {},
            onDeleteClick = { /* TODO */ },
            onAddClick = { /* TODO */ },
            onEditClick = {}
        )

    }
}