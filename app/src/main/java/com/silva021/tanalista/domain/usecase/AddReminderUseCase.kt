package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ReminderRepository
import com.silva021.tanalista.domain.model.ShoppingReminder

class AddReminderUseCase(private val repository: ReminderRepository) {
    suspend operator fun invoke(reminder: ShoppingReminder) = repository.addReminder(reminder)
}
