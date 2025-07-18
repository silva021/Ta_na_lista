package com.silva021.tanalista.ui.screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.components.Description
import com.silva021.designsystem.components.SubTitle
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.TypographyApp

@Composable
fun LoadingScreen(
    message: String = "Carregando...",
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp),
            color = Palette.buttonColor,
            strokeWidth = 4.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        SubTitle(
            text = message,
            color = Palette.buttonColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
@Preview
fun LoadingScreenPreview() {
    LoadingScreen()
}