package com.silva021.tanalista.work

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

object ReminderScheduler {
    fun schedule(context: Context, itemName: String, reminderTime: LocalDateTime) {
        val delay = Duration.between(LocalDateTime.now(), reminderTime).toMillis()
        val data = Data.Builder()
            .putString(ReminderWorker.KEY_ITEM_NAME, itemName)
            .build()
        val request = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(delay, java.util.concurrent.TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()
        WorkManager.getInstance(context).enqueue(request)
    }
}
