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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.silva021.tanalista.R

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
                backgroundColor = Palette.backgroundColor,
                elevation = 0.dp,
                title = { Text("Sobre o app") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SubTitle("📱 Tá na Lista", fontWeight = FontWeight.Bold)
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