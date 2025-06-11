package com.silva021.tanalista.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.ui.theme.Palette.FieldBackground
import kotlin.collections.forEach
import com.silva021.tanalista.R


@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun CategorySelector(
    categories: List<CategoryType>,
    categorySelected: CategoryType,
    onCategorySelected: (CategoryType) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            stringResource(id = R.string.categories),
            style = MaterialTheme.typography.h6
        )
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
                    colors = ChipDefaults.filterChipColors(
                        backgroundColor = FieldBackground,
                        contentColor = Palette.Black,
                        leadingIconColor = Palette.Black,
                        selectedBackgroundColor =  Palette.buttonColor,
                        selectedContentColor = Palette.White,
                        selectedLeadingIconColor = Palette.White
                    ),
                    content = { Text(stringResource(id = type.labelRes)) },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(start = 6.dp).size(20.dp),
                            imageVector = type.icon,
                            contentDescription = null
                        )
                    },
                    shape = RoundedCornerShape(16.dp)

                )
            }
        }
    }
}