package com.silva021.tanalista.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.ui.theme.Palette.PrimaryBeige
import com.silva021.tanalista.ui.theme.Palette.TextDarkGray

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                color = TextDarkGray,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.button,
                textAlign = TextAlign.Center
            ) },
        modifier = modifier.fillMaxWidth(),
        leadingIcon = leadingIcon,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            textColor = TextDarkGray,
            disabledTextColor = TextDarkGray.copy(alpha = 0.5f),
            backgroundColor = PrimaryBeige.copy(alpha = 0.7f),
            cursorColor = TextDarkGray,
            focusedIndicatorColor = TextDarkGray,
            unfocusedIndicatorColor = TextDarkGray.copy(alpha = 0.5f),
            disabledIndicatorColor = Color.Transparent,
            focusedLabelColor = TextDarkGray,
            unfocusedLabelColor = TextDarkGray.copy(alpha = 0.7f),
            disabledLabelColor = TextDarkGray.copy(alpha = 0.5f),
            leadingIconColor = TextDarkGray,
            trailingIconColor = TextDarkGray,
            disabledLeadingIconColor = TextDarkGray.copy(alpha = 0.5f),
            disabledTrailingIconColor = TextDarkGray.copy(alpha = 0.5f)
        ),
        isError = isError,
        enabled = enabled,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun CustomIcon(icon: ImageVector) {
    Icon(
        icon,
        contentDescription = null,
        tint = Palette.AccentGold
    )
}