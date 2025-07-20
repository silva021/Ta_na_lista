package com.silva021.tanalista.ui.screen.shopping.add.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.theme.AppShapes
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.textFieldDefaultColors
import com.silva021.tanalista.domain.model.UnitType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(selected: String, onSelect: (UnitType) -> Unit) {
    val unitiesType = UnitType.entries.toList()
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            modifier = Modifier
                .menuAnchor()
                .width(160.dp)
                .clickable { expanded = true },
            readOnly = true,
            shape = AppShapes.Rounded,
            singleLine = true,
            colors = textFieldDefaultColors(),
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        )

        ExposedDropdownMenu(
            containerColor = Palette.White,
            border = BorderStroke(
                width = 1.dp,
                color = Palette.backgroundColor
            ),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            unitiesType.forEach { unityType ->
                DropdownMenuItem(
                    colors = MenuDefaults.itemColors().copy(
                        textColor = Palette.TextDarkGray,
                        leadingIconColor = Palette.Green,
                        trailingIconColor = Palette.PrimaryBeige,
                        disabledTextColor = Palette.PrimarySepia,
                        disabledLeadingIconColor = Red,
                        disabledTrailingIconColor = Yellow
                    ),
                    text = { Text(unityType.label) },
                    onClick = {
                        onSelect(unityType)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DropdownMenuBoxPreview() {
    var selectedUnit by remember { mutableStateOf(UnitType.KILOGRAM) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        DropdownMenuBox(
            selected = selectedUnit.label,
            onSelect = { selectedUnit = it }
        )
    }
}

