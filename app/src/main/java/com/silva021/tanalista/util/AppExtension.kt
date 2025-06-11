package com.silva021.tanalista.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface
import android.media.MediaPlayer
import android.text.Html
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.google.gson.Gson
import com.silva021.tanalista.ui.theme.Scaffold
import java.security.MessageDigest


val CURRENCY_PRECISE = "R$ %.2f"
val EMPTY_STRING = ""

fun Float?.toCurrencyFormat(): String {
    this ?: return EMPTY_STRING
    return String.format(CURRENCY_PRECISE, this)
}

fun String.fromHtml(): AnnotatedString {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toAnnotatedString()
}

fun String.toHash(): String {
    val bytes = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}

private fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                Typeface.BOLD_ITALIC -> addStyle(
                    SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic),
                    start,
                    end
                )
            }
        }
    }
}

@Composable
fun Context.hideKeyboard() {
    LocalFocusManager.current.clearFocus()
}

@Composable
fun ThemedScreen(content: @Composable () -> Unit) {
//    TaNaListaTheme {
        Scaffold {
            content()
        }
//    }
}

fun Context.playSound(soundResId: Int) {
    MediaPlayer.create(this, soundResId)?.apply {
        setVolume(0.1f, 0.1f)
        start()
        setOnCompletionListener { release() }
    }
}

fun String.copyToClipboard(context: Context, onBlock: () -> Unit) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("texto", this)
    clipboardManager.setPrimaryClip(clipData)
    onBlock.invoke()
}

fun Any.toJson(): String {
    val json = Gson().toJson(this)
    Log.d("lucas-debug", json)
    return json
}

inline fun <reified T> String.fromJson(): T {
    Log.d("lucas-debug", this)
    return Gson().fromJson(this, T::class.java)
}