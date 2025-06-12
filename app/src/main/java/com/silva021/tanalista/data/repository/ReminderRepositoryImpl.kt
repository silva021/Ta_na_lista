package com.silva021.tanalista.data.repository

import com.silva021.tanalista.data.local.room.dao.ShoppingReminderDao
import com.silva021.tanalista.domain.mappers.toEntity
import com.silva021.tanalista.domain.mappers.toModel
import com.silva021.tanalista.domain.model.ShoppingReminder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReminderRepositoryImpl(
    private val dao: ShoppingReminderDao
) : ReminderRepository {
    override fun getAllReminders(): Flow<List<ShoppingReminder>> =
        dao.getAll().map { list -> list.map { it.toModel() } }

    override suspend fun addReminder(reminder: ShoppingReminder) {
        dao.insert(reminder.toEntity())
    }

    override suspend fun updateReminder(reminder: ShoppingReminder) {
        dao.update(reminder.toEntity())
    }

    override suspend fun deleteReminder(reminder: ShoppingReminder) {
        dao.delete(reminder.toEntity())
    }
}
