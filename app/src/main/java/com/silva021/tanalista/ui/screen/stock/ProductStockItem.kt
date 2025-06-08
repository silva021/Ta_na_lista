package com.silva021.tanalista.ui.screen.stock

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.util.ThemedScreen
import com.silva021.tanalista.domain.model.StockItem
import com.silva021.tanalista.domain.model.StockStatus
import com.silva021.tanalista.domain.model.UnitType

@Composable
fun ProductStockItem(item: StockItem, onEditClick: (StockItem) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.height(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(item.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(Modifier.weight(1f))

                IconButton(
                    onClick = { onEditClick(item) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = Color(0xFF1C3D3A)
                    )
                }
            }


            Text("${item.currentQuantity} ${item.unitType.label}", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))

            StockProgressBar(
                percent = item.percentRemaining,
                color = item.status.barColor
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                item.status.apply {
                    Surface(
                        color = this.textColor,
                        shape = RoundedCornerShape(12.dp)
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

                Surface(
                    color = Color(0xFFF3F3F3),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .clickable { onEditClick(item) }
                ) {
                    Text(
                        "Repor",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Surface(
                    color = Color(0xFFF3F3F3),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .clickable { onEditClick(item) }
                ) {
                    Text(
                        "Consumir",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

val StockItem.percentRemaining: Float
    get() = if (minRequired > 0f) {
        (currentQuantity / minRequired).coerceIn(0f, 1f)
    } else {
        1f
    }

@Composable
fun StockProgressBar(
    percent: Float,
    color: Color,
    backgroundColor: Color = Color(0xFFE5E5E5),
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(percent.coerceIn(0f, 1f))
                .fillMaxHeight()
                .clip(RoundedCornerShape(50))
                .background(color)
                .animateContentSize()
        )
    }
}

@Preview
@Composable
fun PreviewProductStockItem() {
    ThemedScreen {
        val item = StockItem(
            id = "arroz",
            name = "Arroz",
            currentQuantity = 2.0f,
            unitType = UnitType.KILOGRAM,
            minRequired = 5.0f,
            status = StockStatus.LOW
        )

        ProductStockItem(
            onEditClick = {},
            item = item,
        )
    }
}