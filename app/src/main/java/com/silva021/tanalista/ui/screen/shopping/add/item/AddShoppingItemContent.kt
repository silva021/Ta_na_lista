package com.silva021.tanalista.ui.screen.shopping.add.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.silva021.designsystem.components.CustomButton
import com.silva021.designsystem.components.Label
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.theme.AppShapes
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Scaffold
import com.silva021.designsystem.theme.textFieldDefaultColors
import com.silva021.designsystem.theme.topBarDefaultColors
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.UnitType
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddShoppingItemContent(
    listId: String,
    shoppingItem: ShoppingItem? = null,
    onAddShoppingItem: (ShoppingItem) -> Unit,
    onEditShoppingItem: (ShoppingItem) -> Unit = {},
    onBackPressed: () -> Unit,
) {
    var name by remember { mutableStateOf(shoppingItem?.name ?: "") }
    var quantity by remember { mutableStateOf(shoppingItem?.quantity?.toString() ?: "") }
    var minimumQuantity by remember { mutableStateOf(shoppingItem?.minRequired?.toString() ?: "") }
    var unit by remember { mutableStateOf(shoppingItem?.unitType ?: UnitType.UNIT) }

    val isEditing = shoppingItem != null
    val itemId = shoppingItem?.id ?: UUID.randomUUID().toString()

    Scaffold(
        topBar = {
            TopAppBar(colors = topBarDefaultColors(), title = { }, navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.content_desc_back)
                    )
                }
            })
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.backgroundColor)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Title(
                text = if (isEditing) stringResource(R.string.title_edit_item) else stringResource(
                    R.string.title_add_item
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = {
                            Text(stringResource(R.string.placeholder_item_name))
                        },
                        placeholder = { Text("Ex: Arroz") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = AppShapes.Rounded,
                        colors = textFieldDefaultColors(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Label(stringResource(R.string.label_unit_type))
                    Spacer(modifier = Modifier.height(6.dp))

                    DropdownMenuBox(unit.label, { unit = it })

                    Spacer(modifier = Modifier.height(12.dp))

                    TextField(
                        value = quantity,
                        onValueChange = {
                            if (it.isDigitsOnly())
                                quantity = it
                        },
                        label = {
                            Text("Quantidade atual")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text("Ex: 6") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = textFieldDefaultColors(),
                        shape = AppShapes.Rounded,
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    TextField(
                        value = minimumQuantity,
                        onValueChange = {
                            if (it.isDigitsOnly())
                                minimumQuantity = it
                        },
                        label = {
                            Text(stringResource(R.string.placeholder_minimum_quantity))
                        },

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text("Ex: 2") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = AppShapes.Rounded,
                        colors = textFieldDefaultColors(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    CustomButton(
                        model = ButtonModel(
                            label = if (isEditing) stringResource(R.string.action_update)
                            else stringResource(
                                R.string.action_save
                            ),
                            onClick = {
                                val item = ShoppingItem(
                                    name = name,
                                    quantity = quantity.toFloat(),
                                    listId = listId,
                                    unitType = unit,
                                    minRequired = minimumQuantity.toFloat()
                                )

                                if (isEditing) {
                                    onEditShoppingItem(item.copy(id = itemId))
                                } else {
                                    onAddShoppingItem(item)
                                }
                            },
                            enabled = name.isNotBlank() && quantity.isNotBlank() && minimumQuantity.isNotBlank()
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddShoppingItemContentPreview() {
    AddShoppingItemContent(
        listId = "12345",
        onAddShoppingItem = { /* salvar */ },
        onBackPressed = { /* voltar */ })
}