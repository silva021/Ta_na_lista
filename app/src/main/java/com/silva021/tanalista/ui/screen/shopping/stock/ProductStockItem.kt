package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.StockStatus
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.ui.theme.Palette.Black
import com.silva021.tanalista.util.ThemedScreen

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
                Text(item.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(Modifier.weight(1f))

                IconButton(
                    onClick = { onDeleteShoppingItem(item) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Black
                    )
                }
            }

            Text("${item.quantity} ${item.unitType.label}", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))

            val stockStatus = StockStatus.calculateStatus(item.quantity, item.minRequired)

            StockProgressBar(
                percent = item.percentRemaining,
                color = stockStatus.barColor
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                stockStatus.apply {
                    Surface(
                        color = this.textColor,
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text(
                            this.label,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                if (item.quantity != 0f)
                    Surface(
                        color = colorResource(id = R.color.consume_button),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .clickable { onAdjustStock(item.copy(quantity = item.quantity - 1)) }
                    ) {
                        Text(
                            stringResource(R.string.action_consume),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = Bold
                        )
                    }

                Spacer(modifier = Modifier.width(6.dp))

                Surface(
                    color = colorResource(id = R.color.replenish_button),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .clickable { onAdjustStock(item.copy(quantity = item.quantity + 1)) }
                ) {
                    Spacer(Modifier.width(4.dp))
                    Text(
                        stringResource(R.string.action_replenish),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = Bold
                    )
                }

            }
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
        val item = ShoppingItem(
            id = "arroz",
            name = "Arroz",
            quantity = 10f,
            unitType = UnitType.KILOGRAM,
            listId = "stock_list_1",
            minRequired = 20f
        )

        ProductStockItem(
            onEditShoppingItem = {},
            onAdjustStock = {},
            onDeleteShoppingItem = {},
            item = item,
        )
    }
}