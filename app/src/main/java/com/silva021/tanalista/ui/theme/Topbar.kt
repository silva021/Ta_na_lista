package com.silva021.tanalista.ui.theme

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.silva021.tanalista.ui.theme.Palette
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pgolcursos.biblequiz.ui.model.TopBarModel
import com.silva021.tanalista.R

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
                        contentDescription = stringResource(id = R.string.back),
                        tint = Palette.Black
                    )
                }
            }
        },
        contentColor = Color.Transparent,
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}