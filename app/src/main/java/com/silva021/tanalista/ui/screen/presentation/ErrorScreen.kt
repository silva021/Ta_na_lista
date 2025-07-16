package com.silva021.tanalista.ui.screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
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

val RedLight = Color(0xFFFFF1F2)
val RedDark = Color(0xFFEF4444)

@Composable
fun ErrorScreen(
    title: String,
    subtitle: String,
    onRetry: (() -> Unit)? = null,
) {
    ThemedScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(RedLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Ícone de erro",
                    tint = RedDark,
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = TypographyApp.h2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = subtitle,
                color = Black,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                style = TypographyApp.body1
            )

            Spacer(modifier = Modifier.weight(1f))

            onRetry?.let {
                CustomButton(
                    model = ButtonModel(
                        onClick = onRetry,
                        label = "Tentar novamente"
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ThemedScreen {
        ErrorScreen(
            title = "Ops! Algo deu errado.",
            subtitle = "Não foi possível carregar os dados. Verifique sua conexão com a internet e tente novamente.",
            onRetry = {}
        )
    }
}