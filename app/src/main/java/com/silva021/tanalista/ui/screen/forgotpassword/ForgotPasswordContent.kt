package com.silva021.tanalista.ui.screen.forgotpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.designsystem.components.CustomButton
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.extension.ThemedScreen
import com.silva021.designsystem.theme.AppShapes
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Scaffold
import com.silva021.designsystem.theme.getTextFieldColors
import com.silva021.tanalista.R
import com.silva021.tanalista.util.isValidEmail

@Composable
fun ForgotPasswordContent(
    onBackPressed: () -> Unit,
    sendPasswordResetEmail: (String) -> Unit,
) {
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Palette.backgroundColor,
                elevation = 0.dp,
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.content_desc_back)
                        )
                    }
                }
            )
        },
    ) { _ ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Palette.backgroundColor)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = stringResource(R.string.logo_desc),
                        tint = Color.Unspecified,
                        modifier = Modifier.size(32.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Title(text = stringResource(R.string.app_name))
                }

                Spacer(modifier = Modifier.height(32.dp))


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color.White)
                        .padding(24.dp),
                ) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text(stringResource(R.string.placeholder_email)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = getTextFieldColors(),
                        shape = AppShapes.Rounded,
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CustomButton(
                        model = ButtonModel(
                            label = stringResource(R.string.action_confirm),
                            onClick = {
                                sendPasswordResetEmail.invoke(email)
                            },
                            enabled = email.isValidEmail()
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ForgotPasswordContentPreview() {
    ThemedScreen {
        ForgotPasswordContent({}, {})
    }
}