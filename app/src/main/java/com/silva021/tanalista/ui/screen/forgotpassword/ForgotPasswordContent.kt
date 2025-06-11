package com.silva021.tanalista.ui.screen.forgotpassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.tanalista.ui.components.CustomButton
import com.silva021.tanalista.ui.components.CustomOutlinedTextField
import com.silva021.tanalista.ui.components.model.ButtonModel
import com.silva021.tanalista.ui.model.TopBarModel
import com.silva021.tanalista.util.ThemedScreen
import com.silva021.tanalista.ui.theme.Scaffold
import com.silva021.tanalista.ui.theme.TopAppBar

@Composable
fun ForgotPasswordContent(
    onBackPressed: () -> Unit,
    sendPasswordResetEmail: (String) -> Unit,
) {
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                topBarModel = TopBarModel(
                    showBackButton = true,
                    onBackClick = onBackPressed
                )
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
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))

//                Icon(
//                    modifier = Modifier.size(130.dp),
//                    painter = painterResource(id = R.drawable.logo),
//                    contentDescription = "null",
//                    tint = Color.Unspecified
//                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomOutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "E-mail",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(
                    model = ButtonModel(
                        label = "Confirmar",
                        onClick = {
                            sendPasswordResetEmail.invoke(email)
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
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