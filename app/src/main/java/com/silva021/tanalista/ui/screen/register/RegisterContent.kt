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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.components.CustomButton
import com.silva021.designsystem.components.Label
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.extension.ThemedScreen
import com.silva021.designsystem.theme.AppShapes
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Palette.backgroundColor
import com.silva021.designsystem.theme.Scaffold
import com.silva021.designsystem.theme.getTextFieldColors
import com.silva021.tanalista.R
import com.silva021.tanalista.ui.model.RegisterScreenModel
import com.silva021.tanalista.util.isValidEmail
import kotlinx.coroutines.launch

@Composable
fun RegisterContent(
    model: RegisterScreenModel,
    onEmailChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    onTermsAndConditions: () -> Unit,
) {

    val scope = rememberCoroutineScope()
    var checked by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

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

                Title(text = stringResource(R.string.app_name))
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
                    value = model.name,
                    onValueChange = onNameChange,
                    placeholder = { Text(stringResource(R.string.placeholder_name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = getTextFieldColors(),
                    shape = AppShapes.Rounded,
                    singleLine = true
                )

                TextField(
                    value = model.email,
                    onValueChange = onEmailChange,
                    placeholder = { Text(stringResource(R.string.placeholder_email)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = getTextFieldColors(),
                    shape = AppShapes.Rounded,
                    singleLine = true
                )

                TextField(
                    value = model.password,
                    onValueChange = onPasswordChange,
                    placeholder = { Text(stringResource(R.string.placeholder_password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = getTextFieldColors(),
                    shape = AppShapes.Rounded,
                    singleLine = true
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        }, colors = CheckboxDefaults.colors(
                            checkedColor = Color.White,
                            uncheckedColor = Palette.TextDarkGray,
                            checkmarkColor = Palette.TextDarkGray,
                        )
                    )
                    Label(
                        "Aceito os <b>Termos e Condições</b>",
                        modifier = Modifier.clickable {
                            onTermsAndConditions.invoke()
                        }
                    )
                }

                CustomButton(
                    ButtonModel(
                        label = stringResource(R.string.action_register),
                        onClick = onRegisterClick,
                        enabled = model.email.isValidEmail() && model.password.isNotBlank() && model.name.isNotBlank() && checked,
                        isLoading = model.isLoading
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Label(
                    stringResource(R.string.text_already_account),
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { onLoginClick() },
                )
            }
        }

        model.errorMessage?.let {
            scope.launch {
                snackbarHostState.showSnackbar(it)
            }
        }

    }
}

@Preview
@Composable
fun RegisterContentPreview() {
    ThemedScreen {
        RegisterContent(
            model = RegisterScreenModel(),
            onEmailChange = {},
            onNameChange = {},
            onPasswordChange = {},
            onRegisterClick = {},
            onLoginClick = {},
            onTermsAndConditions = {}
        )
    }
}