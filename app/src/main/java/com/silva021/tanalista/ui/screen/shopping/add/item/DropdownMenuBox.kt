package com.silva021.tanalista.ui.screen.shopping.add.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.UnitType

@OptIn(ExperimentalMaterialApi::class)
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
                .width(160.dp)
                .clickable { expanded = true },
            readOnly = true,
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.textfield_bg),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            unitiesType.forEach { unitType ->
                DropdownMenuItem(
                    content = { Text(unitType.label) },
                    onClick = {
                        onSelect(unitType)
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

