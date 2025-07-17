package com.silva021.tanalista.ui.screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.silva021.tanalista.R
import com.silva021.tanalista.ui.components.CustomButton
import com.silva021.tanalista.ui.components.model.ButtonModel
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.ui.theme.TypographyApp
import com.silva021.tanalista.util.ThemedScreen

val RedDark = Color(0xFFEF4444)

@Composable
fun ErrorScreen(
    description: String,
    onRetry: (() -> Unit)? = null,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        speed = 1f,
        iterations = 1
    )

    ThemedScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.backgroundColor)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = description,
                color = RedDark,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                style = TypographyApp.body1
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            onRetry?.let {
                Spacer(modifier = Modifier.weight(1f))

                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    model = ButtonModel(
                        onClick = onRetry,
                        label = stringResource(R.string.action_retry),
                        backgroundColor = Color(0xFFB00020),
                        textColor = Color(0xFFFFE5E5)
                    )
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ThemedScreen {
        ErrorScreen(
            description = "Não foi possível carregar os dados. Verifique sua conexão com a internet e tente novamente.",
            onRetry = {}
        )
    }
}