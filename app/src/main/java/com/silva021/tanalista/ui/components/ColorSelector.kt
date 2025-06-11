package com.silva021.tanalista.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.silva021.tanalista.ui.theme.Palette
import androidx.compose.ui.unit.dp
import com.silva021.tanalista.domain.model.ListColor
import com.silva021.tanalista.R
import kotlin.collections.forEach

@Composable
fun ColorSelector(
    colors: List<ListColor>,
    colorSelected: ListColor?,
    onColorSelected: (ListColor) -> Unit,
) {
    Column {
        Text(stringResource(id = R.string.list_color), style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            colors.forEach { cor ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(cor.color)
                        .border(
                            width = if (cor == colorSelected) 1.dp else 0.dp,
                            color = if (cor == colorSelected) Palette.Black else Color.Gray,
                            shape = CircleShape
                        )
                        .clickable { onColorSelected(cor) }
                )
            }
        }
    }
}