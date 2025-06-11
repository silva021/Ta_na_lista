package com.silva021.tanalista.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.silva021.tanalista.ui.theme.Palette
import com.silva021.tanalista.R

enum class CategoryType(
    @StringRes val labelRes: Int,
    val icon: ImageVector,
    val color: Color
) {
    GROCERY(R.string.category_grocery, Icons.Default.ShoppingCart, Palette.CategoryGrocery),
    PET(R.string.category_pet, Icons.Default.Pets, Palette.CategoryPet),
    CLEANING(R.string.category_cleaning, Icons.Default.DeleteSweep, Palette.CategoryCleaning),
    PHARMACY(R.string.category_pharmacy, Icons.Default.MedicalServices, Palette.CategoryPharmacy),
    OTHER(R.string.category_other, Icons.Default.CreditCard, Palette.CategoryOther)
}