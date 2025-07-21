package com.silva021.tanalista.domain.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

data class ShoppingList(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: CategoryType,
    val ownerUID: String,
    val ownerName: String,
    val isMine: Boolean,
    val participants: List<String> = emptyList(),
    val items: List<ShoppingItem> = emptyList(),
    val lastUpdate: Long? = null
)

fun Long.formatLastUpdate(): String {
    val locale = Locale.getDefault()
    val now = Calendar.getInstance()
    val update = Calendar.getInstance().apply { timeInMillis = this@formatLastUpdate }

    val dateFormatTime = SimpleDateFormat("HH:mm", locale)
    val dateFormatDate = SimpleDateFormat("dd/MM/yyyy", locale)

    return when {
        now.get(Calendar.YEAR) == update.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) == update.get(Calendar.DAY_OF_YEAR) ->
            "Hoje ${dateFormatTime.format(update.time)}"

        now.get(Calendar.YEAR) == update.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) - update.get(Calendar.DAY_OF_YEAR) == 1 ->
            "Ontem"

        else -> dateFormatDate.format(update.time)
    }
}
