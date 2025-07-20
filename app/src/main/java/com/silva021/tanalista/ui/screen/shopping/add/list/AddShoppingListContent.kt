package com.silva021.tanalista.ui.screen.shopping.add.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.silva021.designsystem.components.CustomButton
import com.silva021.designsystem.components.Label
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.extension.ThemedScreen
import com.silva021.designsystem.theme.AppShapes
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Scaffold
import com.silva021.designsystem.theme.textFieldDefaultColors
import com.silva021.designsystem.theme.topBarDefaultColors
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddShoppingListContent(
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
        topBar = {
            TopAppBar(
                colors = topBarDefaultColors(),
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = stringResource(R.string.content_desc_back))
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette.backgroundColor)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Title(text = stringResource(R.string.app_name),)

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
                    label = { Text(stringResource(R.string.placeholder_list_name)) },
                    placeholder = { Text("Ex: Mercadinho") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = textFieldDefaultColors(),
                    shape = AppShapes.Rounded
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

                CustomButton(
                    model = ButtonModel(
                        label = if (isEditing) stringResource(R.string.action_edit_list) else stringResource(
                            R.string.action_create_list
                        ),
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
                                        type = categoriesSelected,
                                        ownerUID = Firebase.auth.uid.orEmpty(),
                                        isMine = true,
                                        ownerName = Firebase.auth.currentUser?.displayName.orEmpty()
                                    )
                                )
                            }
                        },
                        enabled = name.isNotBlank()
                    ),
                )
            }
        }
    }
}


@Composable
fun CategorySelector(
    categories: List<CategoryType>,
    categorySelected: CategoryType,
    onCategorySelected: (CategoryType) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Label(stringResource(R.string.label_categories))
        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            maxItemsInEachRow = 4
        ) {
            categories.forEach { type ->
                FilterChip(
                    selected = (type == categorySelected),
                    onClick = { onCategorySelected(type) },
                    colors = FilterChipDefaults.filterChipColors()
                        .copy(
                            selectedContainerColor = type.color.copy(alpha = 0.2f),
                            selectedLabelColor = type.color,
                            leadingIconColor = type.color
                        ),
                    label = { Text(type.label) },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(start = 6.dp).size(20.dp),
                            imageVector = type.icon,
                            tint = type.color,
                            contentDescription = null
                        )
                    },
                    shape = RoundedCornerShape(16.dp)

                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCreateListContent() {
    ThemedScreen {
        AddShoppingListContent(
            onCreateClick = { /* criar lista */ },
            onBackPressed = {}
        )

    }
}