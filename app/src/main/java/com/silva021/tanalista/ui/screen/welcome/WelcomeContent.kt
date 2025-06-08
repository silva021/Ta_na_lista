package com.silva021.tanalista.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.util.ThemedScreen
import com.silva021.tanalista.R
import com.silva021.tanalista.ui.theme.Palette.backgroundColor
import com.silva021.tanalista.ui.theme.Palette.buttonColor

@Composable
fun WelcomeContent(
    onStartClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tá na Lista",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2B2B2B)
        )

        Image(
            painter = painterResource(id = R.drawable.shopping_illustration),
            contentDescription = "Ilustração de carrinho, lista e sacola",
            modifier = Modifier
                .height(200.dp)
                .padding(vertical = 16.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Organize suas compras,\ncontrole seu estoque e nunca\nesqueça nada.",
            textAlign = TextAlign.Center,
            color = Color(0xFF2B2B2B),
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Button(
            onClick = onStartClick,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Começar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview
@Composable
fun RegisterContentPreview() {
    ThemedScreen {
        WelcomeContent(onStartClick = {})
    }
}