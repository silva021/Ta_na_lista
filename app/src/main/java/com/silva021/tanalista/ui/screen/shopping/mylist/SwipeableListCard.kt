package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingList

@Composable
fun SwipeableListCard(
    shoppingList: ShoppingList,
    onCardClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit,
    onDeleteClick: (ShoppingList) -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            when (dismissValue) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onEditClick(shoppingList)
                    false
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    onDeleteClick(shoppingList)
                    false
                }
                else -> {
                    false
                }
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        gesturesEnabled = shoppingList.isMine,
        backgroundContent = {
            val direction = dismissState.dismissDirection
            val color = when (direction) {
                SwipeToDismissBoxValue.StartToEnd -> Color(0xFF4CAF50) // Verde
                SwipeToDismissBoxValue.EndToStart -> Color(0xFFF44336)  // Azul
                else -> null
            }

            val icon = when (direction) {
                SwipeToDismissBoxValue.StartToEnd -> Icons.Default.Edit
                SwipeToDismissBoxValue.EndToStart -> Icons.Default.Delete
                else -> null
            }

            val alignment = when (direction) {
                SwipeToDismissBoxValue.StartToEnd -> Alignment.CenterStart
                SwipeToDismissBoxValue.EndToStart -> Alignment.CenterEnd
                else -> null
            }

            if (shoppingList.isMine) {
                color?.let {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = 20.dp),
                        contentAlignment = alignment!!
                    ) {
                        Icon(
                            imageVector = icon!!,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        },
        content = {
            Column(
                Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp)
            ) {
                ListCard(
                    shoppingList = shoppingList,
                    onCardClick = onCardClick
                )
            }
        }
    )
}

@Composable
@Preview
fun PreviewSwipeableListCard() {
    val shoppingList = ShoppingList(
        id = "1",
        name = "Lista de Compras",
        type = CategoryType.GROCERY,
        items = listOf(),
        isMine = true,
        ownerName = "John Doe",
        ownerUID = "1234567890",
        lastUpdate = System.currentTimeMillis()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SwipeableListCard(
            shoppingList = shoppingList,
            onDeleteClick = {},
            onEditClick = {},
            onCardClick = { /* Handle card click */ }
        )

        Spacer(modifier = Modifier.height(4.dp))

        SwipeableListCard(
            shoppingList = shoppingList.copy(isMine = false),
            onDeleteClick = {},
            onEditClick = {},
            onCardClick = { /* Handle card click */ }
        )
    }
}