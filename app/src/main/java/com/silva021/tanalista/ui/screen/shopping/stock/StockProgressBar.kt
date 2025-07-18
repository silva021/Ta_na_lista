package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.theme.Palette

@Composable
fun StockProgressBar(
    percent: Float,
    color: Color,
    backgroundColor: Color = Palette.progressColor,
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
fun StockProgressBarPreview() {
    StockProgressBar(
        percent = 0.7f,
        color = Color.Green,
        backgroundColor = Color.LightGray
    )
}