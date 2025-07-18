package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.People
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
import com.silva021.designsystem.components.Description
import com.silva021.designsystem.components.SubTitle
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.extension.ThemedScreen
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Scaffold
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingList

@Composable
fun ShoppingListsContent(
    lists: List<ShoppingList>,
    onCardClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit,
    onDeleteClick: (ShoppingList) -> Unit,
    onAddClick: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
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
            Title(text = stringResource(R.string.title_my_lists),)

            Spacer(modifier = Modifier.height(24.dp))

            if (lists.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SubTitle(
                        stringResource(R.string.text_no_lists),
                        fontWeight = FontWeight.Bold
                    )
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

@Composable
fun ListCard(
    shoppingList: ShoppingList,
    onCardClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit,
    onDeleteClick: (ShoppingList) -> Unit,
) {
    val backgroundColor = shoppingList.type.color

    Card(
        shape = RoundedCornerShape(24.dp),
        backgroundColor = backgroundColor,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCardClick.invoke(shoppingList)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (shoppingList.isMine.not()) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.People,
                        tint = Color.White,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = shoppingList.type.icon,
                        tint = Color.White,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Description(text = shoppingList.name, color = Color.White, fontWeight = FontWeight.Bold)
            }

            Row {
                IconButton(onClick = { onEditClick(shoppingList) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(R.string.action_edit_list),
                        tint = Color.White
                    )
                }

                IconButton(onClick = { onDeleteClick(shoppingList) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.action_delete),
                        tint = Color.White
                    )
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
                sharedWith = listOf()
            ),
            ShoppingList(
                name = "Farmácia",
                type = CategoryType.PHARMACY,
                ownerUID = "1234567890",
                ownerName = "João Silva",
                isMine = false,
                sharedWith = listOf()
            )
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