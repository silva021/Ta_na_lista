package com.silva021.tanalista.ui.screen.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.base.ConfirmActionDialog
import com.silva021.designsystem.components.Description
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Palette.White
import com.silva021.designsystem.theme.Scaffold
import com.silva021.designsystem.theme.topBarDefaultColors
import com.silva021.tanalista.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountContent(
    onRateApp: () -> Unit,
    onAboutApp: () -> Unit,
    onTermsAndConditions: () -> Unit,
    onDeleteAccount: () -> Unit,
    onPolicyPrivacy: () -> Unit,
    onResetPassword: () -> Unit,
    onLogout: () -> Unit,
    onBackPressed: () -> Unit = {},
) {
    val showLogoutDialog = remember { mutableStateOf(false) }
    val showDeleteDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topBarDefaultColors(),
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.content_desc_back)
                        )
                    }
                },
                title = {
                    Text(
                        text = "Conta",
                        color = Palette.Black,
                        textAlign = TextAlign.Center,
                    )
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (showLogoutDialog.value) {
                ConfirmActionDialog(
                    title = "Sair da Conta",
                    message = "Tem certeza de que deseja sair da sua conta?",
                    onConfirm = {
                        showLogoutDialog.value = false
                        onLogout.invoke()
                    },
                    onDismiss = { showLogoutDialog.value = false }
                )
            }

            if (showDeleteDialog.value) {
                ConfirmActionDialog(
                    title = "Excluir Conta",
                    message = "Tem certeza de que deseja excluir sua conta? Esta ação é irreversível.",
                    onConfirm = {
                        showDeleteDialog.value = false
                        onDeleteAccount.invoke()
                    },
                    onDismiss = { showDeleteDialog.value = false }
                )
            }

            OptionsSection(
                "Perfil"
            ) {
                SettingsItem(
                    text = "Alterar Senha",
                    icon = Icons.Default.Lock,
                    onClick = onResetPassword
                )
                SettingsItem(
                    text = "Sair da Conta",
                    icon = Icons.AutoMirrored.Filled.ExitToApp,
                    onClick = { showLogoutDialog.value = true }
                )
            }

            OptionsSection("Sobre") {
                SettingsItem(
                    text = "Termos de Uso",
                    icon = Icons.Default.Description,
                    onClick = onTermsAndConditions
                )
                SettingsItem(
                    text = "Política de Privacidade",
                    icon = Icons.Default.Lock,
                    onClick = onPolicyPrivacy
                )
                SettingsItem(
                    text = "Avaliar Aplicativo",
                    icon = Icons.Default.Star,
                    onClick = onRateApp
                )
                SettingsItem(
                    text = "Sobre o app",
                    icon = Icons.Default.Info,
                    onClick = onAboutApp
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            OptionsSection(
                "Conta"
            ) {
                SettingsItem(
                    text = "Excluir Conta",
                    icon = Icons.Default.Delete,
                    onClick = { showDeleteDialog.value = true }
                )

            }
        }
    }
}

@Composable
fun OptionsSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Description(
        text = title,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Column(content = content)
}

@Composable
fun SettingsItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Palette.Black
        )

        Spacer(modifier = Modifier.width(16.dp))

        Description(
            text = text,
            modifier = Modifier.weight(1f),
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
@Preview
fun AccountContentPreview() {
    AccountContent(
        onRateApp = {},
        onPolicyPrivacy = {},
        onResetPassword = {},
        onTermsAndConditions = {},
        onDeleteAccount = {},
        onAboutApp = {},
        onLogout = {},
    )
}