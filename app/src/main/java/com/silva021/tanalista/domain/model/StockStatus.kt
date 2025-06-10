package com.silva021.tanalista.domain.model

import androidx.compose.ui.graphics.Color

enum class StockStatus(
    val label: String,
    val barColor: Color,
    val textColor: Color
) {
    EMPTY(
        label = "Sem estoque",
        barColor = Color(0xFFBDBDBD),
        textColor = Color(0xFF616161)
    ),
    CRITICAL(
        label = "Muito baixo",
        barColor = Color(0xFFE57373),
        textColor = Color(0xFFC62828)
    ),
    LOW(
        label = "Baixo",
        barColor = Color(0xFFFFF176),
        textColor = Color(0xFFF57F17)
    ),
    OK(
        label = "Bom estoque",
        barColor = Color(0xFF81C784),
        textColor = Color(0xFF2E7D32)
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