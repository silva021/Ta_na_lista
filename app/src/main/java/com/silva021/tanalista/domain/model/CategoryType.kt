package com.silva021.tanalista.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class CategoryType(
    val label: String,
    val icon: ImageVector,
    val color: Color
) {
    GROCERY("Mercado", Icons.Default.ShoppingCart, Color(0xFF4CAF50)),
    PET("Pet", Icons.Default.Pets, Color(0xFFFFB300)),
    CLEANING("Limpeza", Icons.Default.DeleteSweep, Color(0xFF2196F3)),
    PHARMACY("Farmácia", Icons.Default.MedicalServices, Color(0xFFD32F2F)),
    OTHER("Outros", Icons.Default.CreditCard, Color(0xFF757575))
}