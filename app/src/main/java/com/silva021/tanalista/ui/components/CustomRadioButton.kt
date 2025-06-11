package com.silva021.tanalista.ui.components

import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.silva021.tanalista.ui.theme.Palette

@Composable
fun CustomRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
) {
    RadioButton(
        modifier = modifier,
        selected = selected,
        enabled = enabled,
        onClick = {
            onClick?.invoke()
        },
        colors = RadioButtonDefaults.colors(
            selectedColor = Palette.White,
            unselectedColor = Color.Gray,
//            disabledSelectedColor = Color.Black,
//            disabledUnselectedColor = Palette.White,
        )
    )
}
