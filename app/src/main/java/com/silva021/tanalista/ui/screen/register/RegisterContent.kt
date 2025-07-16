package com.silva021.tanalista.ui.screen.register

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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.R
import com.silva021.tanalista.ui.components.CustomButton
import com.silva021.tanalista.ui.components.model.ButtonModel
import com.silva021.tanalista.util.ThemedScreen
import com.silva021.tanalista.ui.theme.Palette.backgroundColor
import com.silva021.tanalista.ui.theme.Palette.buttonColor

@Composable
fun RegisterContent(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

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
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(stringResource(R.string.placeholder_name)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.textfield_bg),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            TextField(
                value = email,
                onValueChange = { email = it },
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
                value = password,
                onValueChange = { password = it },
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
                ButtonModel(
                    label = stringResource(R.string.action_register),
                    onClick = onRegisterClick,
                    enabled = email.isNotBlank() && password.isNotBlank() && name.isNotBlank()
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.text_already_account),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { onLoginClick() },
                color = colorResource(id = R.color.dark_text)
            )
        }
    }
}

@Preview
@Composable
fun RegisterContentPreview() {
    ThemedScreen {
        RegisterContent(
            onRegisterClick = {},
            onLoginClick = {},
        )
    }
}