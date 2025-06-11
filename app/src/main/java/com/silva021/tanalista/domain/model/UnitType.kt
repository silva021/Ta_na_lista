package com.silva021.tanalista.domain.model

import androidx.annotation.StringRes
import com.silva021.tanalista.R

enum class UnitType(@StringRes val labelRes: Int) {
    UNIT(R.string.unit_unit),
    DOZEN(R.string.unit_dozen),
    GRAM(R.string.unit_gram),
    KILOGRAM(R.string.unit_kilogram),
    MILLILITER(R.string.unit_milliliter),
    LITER(R.string.unit_liter),
    PACKAGE(R.string.unit_package),
    BOX(R.string.unit_box),
    OTHER(R.string.unit_other)
}