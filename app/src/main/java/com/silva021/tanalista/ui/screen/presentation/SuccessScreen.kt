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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.silva021.designsystem.components.CustomButton
import com.silva021.designsystem.components.Description
import com.silva021.designsystem.components.SubTitle
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.extension.ThemedScreen
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.TypographyApp
import com.silva021.tanalista.R
import kotlinx.coroutines.delay

@Composable
fun SuccessScreen(
    description: String? = null,
    firstButton: ButtonModel? = null,
    onBackPressed: (() -> Unit)? = null,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.check_success))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        speed = 1f,
        iterations = 1
    )

    LaunchedEffect(progress) {
        if (progress == 1f) {
            delay(500)
            onBackPressed?.invoke()
        }
    }

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
                modifier = Modifier.size(80.dp)
            )

            description?.let {
                Spacer(modifier = Modifier.height(20.dp))

                SubTitle(
                    text = description,
                    color = Palette.buttonColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )

            }
            firstButton?.let {
                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    model = firstButton,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun SuccessScreenPreview() {
//    ThemedScreen {
//        SuccessScreen(
//            description = stringResource(R.string.list_created_description),
//            firstButton = ButtonModel("teste", {})
//        )
//    }
//}
