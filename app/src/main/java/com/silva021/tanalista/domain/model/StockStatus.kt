package com.silva021.tanalista.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.R

enum class StockStatus(
    @StringRes val labelRes: Int,
    val barColor: Color,
    val textColor: Color
) {
    EMPTY(
        labelRes = R.string.stock_empty,
        barColor = Palette.StatusBarEmpty,
        textColor = Palette.StatusTextEmpty
    ),
    CRITICAL(
        labelRes = R.string.stock_critical,
        barColor = Palette.StatusBarCritical,
        textColor = Palette.StatusTextCritical
    ),
    LOW(
        labelRes = R.string.stock_low,
        barColor = Palette.StatusBarLow,
        textColor = Palette.StatusTextLow
    ),
    OK(
        labelRes = R.string.stock_ok,
        barColor = Palette.StatusBarOk,
        textColor = Palette.StatusTextOk
    );

    companion object {
        fun calculateStatus(current: Float, min: Float): StockStatus {
            if (current <= 0f) return EMPTY
            if (min == 0f) return OK

            val percent = current / min
            return when {
                percent <= 0.25f -> CRITICAL
                percent <= 0.6f -> LOW
                else -> OK
            }
        }
    }
}