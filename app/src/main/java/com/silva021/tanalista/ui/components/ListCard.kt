package com.silva021.tanalista.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.People
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.ShoppingList

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

                Text(
                    text = shoppingList.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
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