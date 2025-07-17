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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.ui.components.ListCard
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.util.ThemedScreen

@Composable
fun ShoppingListsContent(
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
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.action_add), tint = Color.White)
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.title_my_lists),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.dark_text)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (lists.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.text_no_lists),
                        fontWeight = FontWeight.Bold)
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

        ShoppingListsContent(
            lists = shoppingLists,
            onCardClick = {},
            onDeleteClick = { /* TODO */ },
            onAddClick = { /* TODO */ },
            onEditClick = {}
        )

    }
}