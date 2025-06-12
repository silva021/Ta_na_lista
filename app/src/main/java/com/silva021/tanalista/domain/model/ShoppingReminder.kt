package com.silva021.tanalista.domain.model

import java.time.LocalDateTime
import java.util.UUID

/**
 * Model used to store an item reminder information.
 */
data class ShoppingReminder(
    val id: String = UUID.randomUUID().toString(),
    val itemId: String,
    val reminderType: ReminderType,
    val nextReminder: LocalDateTime
)
