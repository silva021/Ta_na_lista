package com.silva021.tanalista.domain.model

import com.silva021.tanalista.domain.model.UnitType.GRAM
import com.silva021.tanalista.domain.model.UnitType.MILLILITER

enum class UnitType(val label: String) {
    UNIT("Unid"),
    DOZEN("dz"),
    GRAM("G"),
    KILOGRAM("KG"),
    MILLILITER("mL"),
    LITER("L"),
    PACKAGE("Pacotes"),
    BOX("Caixa"),
    OTHER("Outro")
}

fun UnitType.formatQuantity(quantity: Float): String {
    val formattedQuantity = when {
        quantity % 1 == 0f -> quantity.toInt().toString()
        else -> String.format("%.2f", quantity).replace(",", ".")
    }

    return when (this) {
        GRAM, MILLILITER -> "$formattedQuantity ${this.label.lowercase()}"
        else -> "$formattedQuantity ${this.label}"
    }
}