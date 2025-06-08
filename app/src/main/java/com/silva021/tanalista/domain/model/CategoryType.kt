package com.silva021.tanalista.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class CategoryType(val label: String, val icon: ImageVector) {
    GROCERY("Mercado", Icons.Default.ShoppingCart),
    PET("Pet", Icons.Default.Pets),
    CLEANING("Limpeza", Icons.Default.DeleteSweep),
    PHARMACY("Farmácia", Icons.Default.MedicalServices),
    OTHER("Outros", Icons.Default.CreditCard)
}