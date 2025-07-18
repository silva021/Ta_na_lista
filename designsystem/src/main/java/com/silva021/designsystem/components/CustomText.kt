package com.silva021.designsystem.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.silva021.designsystem.extension.fromHtml
import com.silva021.designsystem.theme.Palette

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Palette.TextDarkGray,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight = FontWeight.Bold,
) {
    Text(
        text = text.fromHtml(),
        modifier = modifier,
        color = color,
        fontSize = 28.sp,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}

@Composable
fun SubTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Palette.TextDarkGray,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text.fromHtml(),
        modifier = modifier,
        color = color,
        fontSize = 22.sp,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}

@Composable
fun Description(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Palette.TextDarkGray,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text.fromHtml(),
        modifier = modifier,
        color = color,
        fontSize = 18.sp,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}

@Composable
fun Label(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Palette.TextDarkGray,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight = FontWeight.Medium,
) {
    Text(
        text = text.fromHtml(),
        modifier = modifier,
        color = color,
        fontSize = 14.sp,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}
