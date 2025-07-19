package com.silva021.designsystem.base

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.silva021.designsystem.components.CustomButton
import com.silva021.designsystem.components.Description
import com.silva021.designsystem.components.SubTitle
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.theme.AppShapes
import com.silva021.designsystem.theme.Palette

@Composable
fun ConfirmActionDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        backgroundColor = Palette.backgroundColor,
        shape = AppShapes.Rounded,
        title = {
            SubTitle(text = title,fontWeight = FontWeight.Bold)
        },
        text = {
            Description(text = message)
        },
        confirmButton = {
            CustomButton(
                model = ButtonModel(
                    onClick = onConfirm,
                    label = "Confirmar",
                    backgroundColor = Palette.White,
                    textColor = Palette.buttonColor,
                )
            )
        },
        dismissButton = {
            CustomButton(
                model = ButtonModel(
                    onClick = onDismiss,
                    label = "Cancelar"
                )
            )
        }
    )
}

@Preview
@Composable
fun ConfirmActionDialogPreview() {
    ConfirmActionDialog(
        title = "Confirme",
        message = "Teste teste teste teste",
        onConfirm = {},
        onDismiss = {}
    )
}