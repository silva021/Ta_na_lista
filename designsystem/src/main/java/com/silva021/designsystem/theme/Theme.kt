package com.silva021.designsystem.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Palette.PrimarySepia,
    secondary = Palette.PrimaryBeige,
    background = Color.Transparent,
    surface = Color(0xFFFFFBFE),
    onPrimary = Palette.White,
    onSecondary = Color.Yellow,
    onBackground = Color.White,
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun textFieldDefaultColors() = TextFieldDefaults.colors().copy(
    errorLabelColor = Palette.TextDarkGray,
    focusedLabelColor = Palette.TextDarkGray,
    unfocusedLabelColor = Palette.TextDarkGray,
    errorCursorColor = Palette.TextDarkGray,
    errorContainerColor = Palette.textFieldBackground,
    focusedContainerColor = Palette.textFieldBackground,
    disabledContainerColor = Palette.textFieldBackground,
    unfocusedContainerColor = Palette.textFieldBackground,
    unfocusedIndicatorColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarDefaultColors() = TopAppBarDefaults.topAppBarColors(
    containerColor = Palette.backgroundColor,
    scrolledContainerColor = Palette.backgroundColor
)

@Composable
fun TaNaListaTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content,
    )
}

