package com.silva021.tanalista.ui.screen.shopping.sharelist

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.silva021.tanalista.R
import com.silva021.tanalista.ui.components.CustomButton
import com.silva021.tanalista.ui.components.model.ButtonModel
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.ui.theme.Palette.TextDarkGray
import com.silva021.tanalista.ui.theme.Palette.White
import com.silva021.tanalista.ui.theme.Palette.buttonColor

@Composable
fun ShareListContent(
    listId: String,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        backgroundColor = Palette.backgroundColor,
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
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = "Compartilhar Lista",
                color = TextDarkGray,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Convide outras pessoas para acessar e editar esta lista com você.",
                color = TextDarkGray,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            val link = "tanalista://shared_list?listId={$listId}"
            QrCodeImage(link)

            Spacer(modifier = Modifier.weight(1f))

            ShareLinkButtons(link)

        }
    }
}

@Composable
fun QrCodeImage(content: String) {
    val bitmap = remember { generateQrCodeBitmap(content) }

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "QR Code",
        modifier = Modifier.size(400.dp)
    )
}

fun generateQrCodeBitmap(content: String, size: Int = 512): Bitmap {
    val bitMatrix = MultiFormatWriter().encode(
        content,
        BarcodeFormat.QR_CODE,
        size,
        size
    )
    val bitmap = createBitmap(size, size, Bitmap.Config.RGB_565)
    for (x in 0 until size) {
        for (y in 0 until size) {
            bitmap[x, y] = if (bitMatrix[x, y]) BLACK else WHITE
        }
    }
    return bitmap
}

@Composable
fun ShareLinkButtons(link: String) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth()) {
        CustomButton(
            model = ButtonModel(
                label = "Copiar link",
                onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("Lista compartilhada", link)
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(context, "Link copiado!", Toast.LENGTH_SHORT).show()
                },
                backgroundColor = White,
                textColor = buttonColor
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        CustomButton(
            model = ButtonModel(
                label = "Compartilhar no WhatsApp",
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        `package` = "com.whatsapp"
                        putExtra(Intent.EXTRA_TEXT, "Veja minha lista compartilhada:\n $link")
                    }
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "WhatsApp não está instalado", Toast.LENGTH_SHORT).show()
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun ShareListContentPreview() {
    ShareListContent(
        listId = "12",
        onBackPressed = {}
    )
}