package com.silva021.designsystem.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.silva021.designsystem.extension.fromHtml
import com.silva021.designsystem.theme.Palette

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Palette.TextDarkGray,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text.fromHtml(),
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        textAlign = textAlign
    )
}

@Composable
fun SubTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Palette.TextDarkGray,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text.fromHtml(),
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.titleMedium,
        textAlign = textAlign
    )
}

@Composable
fun Description(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Palette.TextDarkGray,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text.fromHtml(),
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold,
        ),
        textAlign = textAlign
    )
}

@Composable
fun Label(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Palette.TextDarkGray,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text.fromHtml(),
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.labelLarge,
        textAlign = textAlign
    )
}