package com.silva021.tanalista.domain.model

import androidx.compose.ui.graphics.Color

enum class StockStatus(
    val label: String,
    val barColor: Color,
    val textColor: Color
) {
    OK(
        label = "Estoque ok",
        barColor = Color(0xFF81C784),     // verde suave
        textColor = Color(0xFF2E7D32)
    ),
    LOW(
        label = "Atenção: estoque baixo",
        barColor = Color(0xFFFFF176),     // amarelo pastel
        textColor = Color(0xFFF57F17)
    ),
    CRITICAL(
        label = "Acabando",
        barColor = Color(0xFFE57373),     // vermelho suave
        textColor = Color(0xFFC62828)
    );

    companion object {
        fun calculateStatus(current: Float, min: Float): StockStatus {
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