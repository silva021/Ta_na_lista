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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.components.CustomButton
import com.silva021.designsystem.components.Label
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.theme.AppShapes
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Scaffold
import com.silva021.designsystem.theme.getTextFieldColors
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.UnitType
import java.util.UUID

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
                })
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.backgroundColor)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Title(text = if (isEditing) stringResource(R.string.title_edit_item) else stringResource(R.string.title_add_item))

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Label(stringResource(R.string.placeholder_item_name))
                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text("Arroz") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = AppShapes.Rounded,
                        colors = getTextFieldColors(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Label(stringResource(R.string.label_unit_type))
                    Spacer(modifier = Modifier.height(6.dp))

                    DropdownMenuBox(unit.label, { unit = it })

                    Spacer(modifier = Modifier.height(12.dp))

                    Label("Quantidade atual")
                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = quantity,
                        onValueChange = { quantity = it },
                        placeholder = { Text("6") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = getTextFieldColors(),
                        shape = AppShapes.Rounded,
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Label(stringResource(R.string.placeholder_minimum_quantity))
                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = minimumQuantity,
                        onValueChange = { minimumQuantity = it },
                        placeholder = { Text("2") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = AppShapes.Rounded,
                        colors = getTextFieldColors(),
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