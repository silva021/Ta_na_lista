package com.silva021.tanalista.ui.screen.shopping.add.shopping

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.ui.theme.Palette

@Composable
fun AddShoppingItemScreen(
    name: String,
    onNameChange: (String) -> Unit,
    quantity: String,
    onQuantityChange: (String) -> Unit,
    unit: String,
    onUnitChange: (String) -> Unit,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    trackStock: Boolean,
    onTrackStockChange: (Boolean) -> Unit,
    onSave: () -> Unit,
    onBackPressed: () -> Unit,
) {

    Scaffold(
        backgroundColor = Palette.backgroundColor,
        topBar = {
            TopAppBar(
                backgroundColor = Palette.backgroundColor,
                elevation = 0.dp,
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.backgroundColor)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                "Adicionar Item",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1C3D3A)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = onNameChange,
                        placeholder = { Text("Nome do item") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color(0xFFF9F7F5),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = quantity,
                            onValueChange = onQuantityChange,
                            placeholder = { Text("Quantidade") },
                            modifier = Modifier.weight(1f),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color(0xFFF9F7F5),
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )

                        DropdownMenuBox(unit, onUnitChange)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Controlar Estoque", fontSize = 16.sp)
                        Switch(
                            checked = trackStock,
                            onCheckedChange = onTrackStockChange,
                        )
                    }

                    if (trackStock) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = onNameChange,
                            placeholder = { Text("Quantidade em estoque") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color(0xFFF9F7F5),
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true
                        )


                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = name,
                            onValueChange = onNameChange,
                            placeholder = { Text("Quantidade em mínima") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color(0xFFF9F7F5),
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Notificar quando abaixo do mínimo", fontSize = 16.sp)
                            Switch(
                                checked = trackStock,
                                onCheckedChange = onTrackStockChange,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = onSave,
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF70A090)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                    ) {
                        Text("Salvar", fontSize = 18.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddShoppingItemScreenPreview() {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("kg") }
    var category by remember { mutableStateOf("Alimentos") }
    var trackStock by remember { mutableStateOf(false) }

    AddShoppingItemScreen(
        name = name,
        onNameChange = { name = it },
        quantity = quantity,
        onQuantityChange = { quantity = it },
        unit = unit,
        onUnitChange = { unit = it },
        selectedCategory = category,
        onCategoryChange = { category = it },
        trackStock = trackStock,
        onTrackStockChange = { trackStock = it },
        onSave = { /* salvar */ },
        onBackPressed = {}
    )
}


@Preview(showBackground = true)
@Composable
fun AddShoppingItemScreenPreview2() {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("kg") }
    var category by remember { mutableStateOf("Alimentos") }
    var trackStock by remember { mutableStateOf(true) }

    AddShoppingItemScreen(
        name = name,
        onNameChange = { name = it },
        quantity = quantity,
        onQuantityChange = { quantity = it },
        unit = unit,
        onUnitChange = { unit = it },
        selectedCategory = category,
        onCategoryChange = { category = it },
        trackStock = trackStock,
        onTrackStockChange = { trackStock = it },
        onSave = { /* salvar */ },
        onBackPressed = { /* voltar */ }
    )
}