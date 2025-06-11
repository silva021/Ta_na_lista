package com.silva021.tanalista.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.silva021.tanalista.ui.theme.Palette.DarkGreen
import com.silva021.tanalista.ui.theme.Palette.TagBackgroundSelected
import com.silva021.tanalista.ui.theme.Palette.TagBackgroundUnselected
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TagOption(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val background = if (selected) TagBackgroundSelected else TagBackgroundUnselected

    Surface(
        color = background,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = DarkGreen,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = label, color = DarkGreen)
        }
    }
}

@Preview
@Composable
fun PreviewTagOption() {
    TagOption(
        label = "Farmácia",
        icon = Icons.Default.MedicalServices,
        selected = true,
        onClick = { }
    )

    TagOption(
        label = "Farmácia",
        icon = Icons.Default.MedicalServices,
        selected = false,
        onClick = { }
    )
}