package com.silva021.tanalista.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.ui.components.model.ButtonModel
import com.silva021.tanalista.ui.theme.getButtonsColors

@Composable
fun CustomButton(
    model: ButtonModel,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    Button(
        onClick = model.onClick,
        modifier = modifier,
        enabled = model.enabled,
        shape = RoundedCornerShape(20),
        colors = getButtonsColors()
    ) {
        Text(
            text = model.label,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.button
        )
    }
}


@Composable
@Preview
fun CustomButtonPreview() {
    CustomButton(
        ButtonModel(
            "Texto",
            {},
            true
        )
    )
}

@Composable
@Preview
fun CustomButtonPreview2() {
    CustomButton(
        ButtonModel(
            "Texto",
            {},
            false
        )
    )
}