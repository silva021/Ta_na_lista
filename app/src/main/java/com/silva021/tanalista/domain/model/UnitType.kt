package com.silva021.tanalista.domain.model

enum class UnitType(val label: String) {
    UNIT("unid."),
    DOZEN("dz"),
    GRAM("g"),
    KILOGRAM("kg"),
    MILLILITER("mL"),
    LITER("L"),
    PACKAGE("pacote"),
    BOX("caixa"),
    OTHER("outro")
}