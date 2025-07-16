package com.silva021.tanalista.ui.screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.tanalista.ui.components.CustomButton
import com.silva021.tanalista.ui.components.model.ButtonModel
import com.silva021.tanalista.ui.theme.Palette.Black
import com.silva021.tanalista.ui.theme.TypographyApp
import com.silva021.tanalista.util.ThemedScreen
import com.silva021.tanalista.util.fromHtml

val GreenDark = Color(0xFF059669)
val GreenLight = Color(0xFFECFDF5)

@Composable
fun SuccessScreen(
    title: String,
    subtitle: String,
    firstButton: ButtonModel
) {
    ThemedScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(GreenLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Heart Icon",
                    tint = GreenDark,
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = title,
                color = Black,
                fontWeight = FontWeight.Bold,
                style = TypographyApp.h2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitle.fromHtml(),
                color = Black,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                style = TypographyApp.body1
            )

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                model = firstButton,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
fun BonusStagePresentationScreenPreview() {
    ThemedScreen {
        SuccessScreen(
            title = "Parabéns!",
            subtitle = "Loren ipsum dolor sit amet, consectetur adipiscing elit. ",
            firstButton = ButtonModel("teste", {})
        )
    }
}
