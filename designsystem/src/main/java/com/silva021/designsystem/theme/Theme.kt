package com.silva021.designsystem.theme

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColors(
    primary = Palette.PrimarySepia,
    secondary = Palette.PrimaryBeige,
    background = Color.Transparent,
    surface = Color(0xFFFFFBFE),
    onPrimary = Palette.Purple80,
    onSecondary = Color.Yellow,
    onBackground = Color.White,
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun getTextFieldColors() = TextFieldDefaults.textFieldColors(
backgroundColor = Palette.textFieldBackground,
unfocusedIndicatorColor = Color.Transparent,
focusedIndicatorColor = Color.Transparent
)

@Composable
fun TaNaListaTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = LightColorScheme,
        typography = TypographyApp,
        content = content,
    )
}

