package com.silva021.tanalista.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.ui.components.model.ButtonModel
import com.silva021.tanalista.ui.theme.Palette.buttonColor

@Composable
fun CustomButton(
    model: ButtonModel,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    Button(
        onClick = model.onClick,
        modifier = modifier
            .height(50.dp),
        enabled = model.enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = model.backgroundColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(
                visible = !model.isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = model.label,
                    color = model.textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.button
                )
            }
            if (model.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = model.textColor,
                    strokeWidth = 2.dp
                )
            }
        }
    }
}


@Composable
@Preview
fun CustomButtonPreview() {
    Column {
        CustomButton(
            ButtonModel(
                "Texto",
                {},
                true
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            ButtonModel(
                "Texto",
                {},
                false
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            ButtonModel(
                label = "Texto",
                onClick = {},
                enabled = true,
                isLoading = true
            )
        )
    }
}
