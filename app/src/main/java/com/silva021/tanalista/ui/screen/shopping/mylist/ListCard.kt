package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.components.Description
import com.silva021.designsystem.components.Label
import com.silva021.designsystem.theme.Palette
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.model.formatLastUpdate

@Composable
fun ListCard(
    shoppingList: ShoppingList,
    onCardClick: (ShoppingList) -> Unit,
) {
    val backgroundColor = shoppingList.type.color

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCardClick.invoke(shoppingList)
            },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors().copy(containerColor = backgroundColor),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = shoppingList.type.icon,
                    tint = Palette.White,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Description(
                    text = shoppingList.name,
                    color = Palette.White,
                )
            }

            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )

            InfoItem(icon = Icons.Default.ShoppingCart, text = "${shoppingList.items.size} itens")

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                shoppingList.lastUpdate?.let {
                    InfoItem(
                        icon = Icons.Default.AccessTime,
                        text = "Atualizado: ${it.formatLastUpdate()}"
                    )
                }

                Spacer(Modifier.weight(1f))

                if (shoppingList.isMine.not()) {
                    Icon(
                        imageVector = Icons.Default.People,
                        contentDescription = "Compartilhada",
                        tint = Palette.White,
                    )
                }
            }
        }
    }
}

@Composable
fun InfoItem(
    icon: ImageVector,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Palette.White,
            modifier = Modifier.size(16.dp)
        )

        Spacer(Modifier.width(4.dp))

        Label(text = text, color = Palette.White)
    }
}

@Composable
@Preview
fun ListCardPreview() {
    val shoppingList = ShoppingList(
        id = "1",
        name = "Lista de Compras",
        type = CategoryType.GROCERY,
        items = listOf(),
        isMine = true,
        ownerName = "",
        ownerUID = "1234567890",
        lastUpdate = System.currentTimeMillis()
    )

    ListCard(
        shoppingList = shoppingList,
        onCardClick = {},
    )
}