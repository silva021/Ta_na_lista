package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.designsystem.components.Description
import com.silva021.designsystem.components.Label
import com.silva021.designsystem.extension.ThemedScreen
import com.silva021.designsystem.theme.Palette
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.StockStatus
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.domain.model.formatQuantity
import com.silva021.tanalista.ui.screen.shopping.mylist.setImageSizeDefault

@Composable
fun ProductStockItem(
    item: ShoppingItem,
    onDeleteShoppingItem: (ShoppingItem) -> Unit,
    onEditShoppingItem: (ShoppingItem) -> Unit,
    onAdjustStock: (ShoppingItem) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onEditShoppingItem(item)
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.height(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Description(item.name, fontWeight = FontWeight.Bold)

                Spacer(Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Palette.Black,
                    modifier = Modifier
                        .setImageSizeDefault()
                        .clickable(
                            role = Role.Button,
                            interactionSource = null,
                            indication = ripple(bounded = false, radius = 24.dp),
                        ) {
                            onDeleteShoppingItem(item)
                        }
                        .testTag("delete_button")
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            val stockStatus = StockStatus.calculateStatus(item.quantity, item.minRequired)

            StockProgressBar(
                percent = item.percentRemaining,
                color = stockStatus.barColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                stockStatus.apply {
                    Surface(
                        color = this.textColor,
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Label(
                            this.label,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            color = Color.White,
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                QuantitySelector(
                    item,
                    onIncrease = {
                        onAdjustStock(item.copy(quantity = item.quantity + 1))
                    },
                    onDecrease = {
                        onAdjustStock(item.copy(quantity = item.quantity - 1))
                    }
                )
            }
        }
    }
}

@Composable
fun QuantitySelector(
    item: ShoppingItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .height(40.dp)
            .widthIn(min = 100.dp)
    ) {
        IconButton(
            onClick = onDecrease,
            modifier = Modifier
                .size(24.dp)
                .testTag("decrease_button"),
            enabled = item.quantity > 0f
        ) {
            Icon(Icons.Default.Remove, contentDescription = "Diminuir")
        }

        Text(
            text = item.unitType.formatQuantity(item.quantity),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        IconButton(
            onClick = onIncrease,
            modifier = Modifier
                .size(24.dp)
                .testTag("increase_button")
        ) {
            Icon(Icons.Default.Add, contentDescription = "Aumentar")
        }
    }
}

val ShoppingItem.percentRemaining: Float
    get() = if (minRequired > 0f) {
        (quantity / minRequired).coerceIn(0f, 1f)
    } else {
        1f
    }

@Preview
@Composable
fun PreviewProductStockItem() {
    ThemedScreen {
        val items = listOf(
            ShoppingItem(
                id = "dozen",
                name = "Ovos",
                quantity = 10f,
                unitType = UnitType.DOZEN,
                listId = "stock_list_1",
                minRequired = 8f
            ),
            ShoppingItem(
                id = "gram",
                name = "Queijo",
                quantity = 10f,
                unitType = UnitType.GRAM,
                listId = "stock_list_1",
                minRequired = 20f
            ),
            ShoppingItem(
                id = "kilogram",
                name = "Arroz",
                quantity = 10f,
                unitType = UnitType.KILOGRAM,
                listId = "stock_list_1",
                minRequired = 20f
            ),
            ShoppingItem(
                id = "milliliter",
                name = "Essência",
                quantity = 10f,
                unitType = UnitType.MILLILITER,
                listId = "stock_list_1",
                minRequired = 15f
            ),
            ShoppingItem(
                id = "liter",
                name = "Leite",
                quantity = 10f,
                unitType = UnitType.LITER,
                listId = "stock_list_1",
                minRequired = 12f
            ),
            ShoppingItem(
                id = "box",
                name = "Caixa de Bombom",
                quantity = 10f,
                unitType = UnitType.BOX,
                listId = "stock_list_1",
                minRequired = 4f
            ),
            ShoppingItem(
                id = "other",
                name = "Outro Item",
                quantity = 10f,
                unitType = UnitType.OTHER,
                listId = "stock_list_1",
                minRequired = 3f
            )
        )

        Column {
            items.forEach {
                ProductStockItem(
                    onEditShoppingItem = {},
                    onAdjustStock = {},
                    onDeleteShoppingItem = {},
                    item = it,
                )
            }
        }
    }
}