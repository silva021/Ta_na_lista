package com.silva021.designsystem.theme

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.components.model.TopBarModel

@Composable
fun TopAppBar(
    topBarModel: TopBarModel,
) {
    TopAppBar(
        title = {
            if (topBarModel.title != null) {
                Text(
                    text = topBarModel.title,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            if (topBarModel.showBackButton) {
                IconButton(onClick = topBarModel.onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.Black
                    )
                }
            }
        },
        contentColor = Color.Transparent,
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}