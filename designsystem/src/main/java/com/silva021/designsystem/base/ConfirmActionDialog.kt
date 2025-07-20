package com.silva021.designsystem.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.silva021.designsystem.components.CustomButton
import com.silva021.designsystem.components.SubTitle
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.theme.AppShapes
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Palette.White

@Composable
fun ConfirmActionDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        )
    ) {
        Surface(
            shape = AppShapes.Rounded,
            tonalElevation = 12.dp,
            modifier = Modifier
                .border(
                    width = 4.dp,
                    color = Palette.backgroundColor,
                    shape = AppShapes.Rounded
                )
        ) {
            Column(
                modifier = Modifier
                    .background(White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Title(text = title)

                Spacer(modifier = Modifier.height(8.dp))

                SubTitle(text = message)

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CustomButton(
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp),
                        model = ButtonModel(
                            onClick = onDismiss,
                            label = "Não",
                            backgroundColor = Palette.White,
                            textColor = Palette.Green,
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CustomButton(
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp),
                        model = ButtonModel(
                            onClick = onConfirm,
                            label = "Sim"
                        )
                    )
                }
            }
        }
    }

//    AlertDialog(
//        tonalElevation = 4.dp,
//        onDismissRequest = onDismiss,
//        containerColor = Palette.backgroundColor,
//        shape = AppShapes.Rounded,
//        title = {
//            Title(text = title)
//        },
//        text = {
//            SubTitle(text = message)
//        },
//        confirmButton = {
//            CustomButton(
//                model = ButtonModel(
//                    onClick = onConfirm,
//                    label = "Confirmar",
//                    backgroundColor = Palette.White,
//                    textColor = Palette.Green,
//                )
//            )
//        },
//        dismissButton = {
//            CustomButton(
//                model = ButtonModel(
//                    onClick = onDismiss,
//                    label = "Cancelar"
//                )
//            )
//        }
//    )
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