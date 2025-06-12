package com.silva021.tanalista.data.repository

import com.silva021.tanalista.domain.model.ShoppingReminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    fun getAllReminders(): Flow<List<ShoppingReminder>>
    suspend fun addReminder(reminder: ShoppingReminder)
    suspend fun updateReminder(reminder: ShoppingReminder)
    suspend fun deleteReminder(reminder: ShoppingReminder)
}
