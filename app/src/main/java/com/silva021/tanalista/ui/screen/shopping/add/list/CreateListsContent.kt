package com.silva021.tanalista.ui.screen.shopping.add.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.ui.components.CategorySelector
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.util.ThemedScreen
import java.util.UUID

@Composable
fun CreateListContent(
    shoppingList: ShoppingList? = null,
    onCreateClick: (ShoppingList) -> Unit,
    onEditClick: (ShoppingList) -> Unit = {},
    onBackPressed: () -> Unit,
) {
    var name by remember { mutableStateOf(shoppingList?.name ?: "") }
    var categoriesSelected by remember { mutableStateOf(shoppingList?.type ?: CategoryType.OTHER) }
    val isEditing = shoppingList != null

    val categories = CategoryType.values().toList()

    Scaffold(
        backgroundColor = Palette.backgroundColor,
        topBar = {
            TopAppBar(
                backgroundColor = Palette.backgroundColor,
                elevation = 0.dp,
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.content_desc_back))
                    }
                }
            )
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
                text = stringResource(R.string.app_name),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.green_text)
            )

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
                    onValueChange = {
                        name = it
                    },
                    placeholder = { Text(stringResource(R.string.placeholder_list_name)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.textfield_bg),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    CategorySelector(
                        categories = categories,
                        categorySelected = categoriesSelected,
                        onCategorySelected = { categoriesSelected = it }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (isEditing) {
                            onEditClick(
                                shoppingList.copy(
                                    name = name,
                                    type = categoriesSelected
                                )
                            )
                        } else {
                            onCreateClick.invoke(
                                ShoppingList(
                                    name = name,
                                    type = categoriesSelected
                                )
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.chip_selected))
                ) {
                    Text(
                        if (isEditing) stringResource(R.string.action_edit_list) else stringResource(R.string.action_create_list),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewCreateListContent() {
    ThemedScreen {
        CreateListContent(
            onCreateClick = { /* criar lista */ },
            onBackPressed = {}
        )

    }
}