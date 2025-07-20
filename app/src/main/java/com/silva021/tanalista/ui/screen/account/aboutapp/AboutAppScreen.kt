package com.silva021.tanalista.ui.screen.account.aboutapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.components.Description
import com.silva021.designsystem.components.Label
import com.silva021.designsystem.components.SubTitle
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Scaffold
import com.silva021.designsystem.theme.topBarDefaultColors
import com.silva021.tanalista.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(
    onBackPressed: () -> Unit,
) {
    val context = LocalContext.current
    val version = try {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        packageInfo.versionName
    } catch (e: Exception) {
        "1.0"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topBarDefaultColors(),
                title = { Text("Sobre o app") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SubTitle("📱 Tá na Lista")
            Description("Este aplicativo foi criado para ajudar você a organizar suas compras de forma simples e prática.")

            Spacer(modifier = Modifier.weight(1f))

            Description("<b>Desenvolvido por:</b> Lucas Silva Sousa")
            Description("<b>Contato:</b> lucasssilva021@gmail.com")
            val uriHandler = LocalUriHandler.current

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    uriHandler.openUri("https://www.linkedin.com/in/devandroidlucas/")
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_linkedin),
                    contentDescription = "LinkedIn",
                    tint = Palette.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Description(
                    text = "<u>Clique e me siga no LinkedIn</u>"
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    uriHandler.openUri("https://github.com/silva021")
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_github),
                    contentDescription = "LinkedIn",
                    tint = Palette.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Description(
                    text = "Clique e me siga no Github"
                )
            }

            Label(
                text = "<b>Versão:</b> $version",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
fun AboutAppScreenPreview() {
    AboutAppScreen(onBackPressed = {})
}