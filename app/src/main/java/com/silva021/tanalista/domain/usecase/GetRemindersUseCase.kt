package com.silva021.tanalista.domain.usecase

import com.silva021.tanalista.data.repository.ReminderRepository

class GetRemindersUseCase(private val repository: ReminderRepository) {
    operator fun invoke() = repository.getAllReminders()
}
