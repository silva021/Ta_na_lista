package com.silva021.tanalista.ui.theme

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColors(
    primary = Palette.PrimarySepia,
    secondary = Palette.PrimaryBeige,
    background = Color.Transparent,
    surface = Palette.SurfaceLight,
    onPrimary = Palette.Purple80,
    onSecondary = Palette.Yellow700,
    onBackground = Palette.White,
    onSurface = Palette.OnSurfaceDark,
)


@Composable
fun getButtonsColors() = ButtonDefaults.buttonColors(
    backgroundColor = Palette.ButtonNormal,
    contentColor = Palette.PrimaryBeige,
    disabledBackgroundColor = Palette.ButtonDisabled,
    disabledContentColor = Color.LightGray
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

