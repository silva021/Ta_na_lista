package com.silva021.tanalista.ui.screen.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.R
import com.silva021.tanalista.ui.components.CustomButton
import com.silva021.tanalista.ui.components.model.ButtonModel
import com.silva021.tanalista.ui.model.LoginScreenModel
import com.silva021.tanalista.ui.theme.Palette.Black
import com.silva021.tanalista.ui.theme.Palette.backgroundColor
import com.silva021.tanalista.util.ThemedScreen
import com.silva021.tanalista.util.isValidEmail

@Composable
fun LoginContent(
    state: LoginScreenModel,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
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

            Text(
                text = stringResource(R.string.app_name),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.dark_text)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            TextField(
                value = state.email,
                onValueChange = onEmailChange,
                placeholder = { Text(stringResource(R.string.placeholder_email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.textfield_bg),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            TextField(
                value = state.password,
                onValueChange = onPasswordChange,
                placeholder = { Text(stringResource(R.string.placeholder_password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.textfield_bg),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            CustomButton(
                model = ButtonModel(
                    label = stringResource(R.string.action_login),
                    onClick = onLoginClick,
                    enabled = state.email.isValidEmail() && state.password.isNotBlank(),
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.text_forgot_password),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { onForgotPasswordClick() },
                color = colorResource(id = R.color.dark_text)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = onGoogleLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, colorResource(id = R.color.google_border)),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.White
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = "Google",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.action_google_login), color = Black)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.text_no_account),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { onRegisterClick() },
                color = colorResource(id = R.color.dark_text)
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    ThemedScreen {
        val state = LoginScreenModel(
            "Lucas",
            "",
            false
        )
        LoginContent(
            state,
            onLoginClick = {},
            onEmailChange = {},
            onPasswordChange = {},
            onGoogleLoginClick = {},
            onForgotPasswordClick = {},
            onRegisterClick = {},
        )

    }

}
