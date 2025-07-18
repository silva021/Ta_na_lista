package com.silva021.tanalista.ui.screen.shopping.showinvite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.silva021.designsystem.components.CustomButton
import com.silva021.designsystem.components.Description
import com.silva021.designsystem.components.Label
import com.silva021.designsystem.components.SubTitle
import com.silva021.designsystem.components.Title
import com.silva021.designsystem.components.model.ButtonModel
import com.silva021.designsystem.theme.AppShapes
import com.silva021.designsystem.theme.Palette
import com.silva021.designsystem.theme.Scaffold
import com.silva021.tanalista.R
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingList

@Composable
fun ShowInviteShoppingListContent(
    shoppingList: ShoppingList,
    onAccept: (ShoppingList) -> Unit,
    onDecline: () -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Palette.backgroundColor,
                elevation = 0.dp,
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.content_desc_back)
                        )
                    }
                },
            )
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title(
                text = "Alguém compartilhou uma lista com você!",
                color = Palette.TextDarkGray,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            SubTitle(
                text = "Toque em “Aceitar” para adicionar essa lista ao seu app e colaborar em tempo real.",
                color = Palette.TextDarkGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            SharedListInviteCard(shoppingList)

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton(
                    model = ButtonModel(
                        label = "Não quero",
                        onClick = onDecline,
                        backgroundColor = Color(0xFFB00020)
                    ),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                CustomButton(
                    model = ButtonModel(
                        label = stringResource(R.string.action_confirm),
                        onClick = { onAccept.invoke(shoppingList) },
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun SharedListInviteCard(
    shoppingList: ShoppingList,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFF2F5F9), AppShapes.Rounded),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = shoppingList.type.icon,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Description(
                        text = shoppingList.name,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Label(
                        text = shoppingList.ownerName,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowInviteShoppingContentPreview() {
    ShowInviteShoppingListContent(
        shoppingList = ShoppingList(
            id = "1",
            name = "Lista de Compras",
            type = CategoryType.GROCERY,
            ownerUID = "owner123",
            ownerName = "João Silva",
            isMine = true
        ),
        onBackPressed = {},
        onAccept = {},
        onDecline = {}
    )
}