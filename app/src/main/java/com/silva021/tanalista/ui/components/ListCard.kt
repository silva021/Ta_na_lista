package com.silva021.tanalista.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.domain.model.ShoppingList

@Composable
fun ListCard(
    shoppingList: ShoppingList,
    onCardClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit,
    onDeleteClick: (ShoppingList) -> Unit,
) {
    val backgroundColor = shoppingList.color.color

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
            Column {
                Text(
                    text = shoppingList.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1C3D3A)
                )
                Text(
                    text = "${shoppingList.items.size} itens",
                    fontSize = 16.sp,
                    color = Color(0xFF1C3D3A)
                )
            }

            Row {
                IconButton(onClick = { onEditClick(shoppingList) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = Color(0xFF1C3D3A)
                    )
                }

                IconButton(onClick = { onDeleteClick(shoppingList) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Deletar",
                        tint = Color(0xFF1C3D3A)
                    )
                }
            }
        }
    }
}