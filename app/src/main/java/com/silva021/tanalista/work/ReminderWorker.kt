package com.silva021.tanalista.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.silva021.tanalista.MainActivity
import com.silva021.tanalista.R

class ReminderWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val itemName = inputData.getString(KEY_ITEM_NAME) ?: return Result.failure()
        createNotificationChannel()
        showNotification(itemName)
        return Result.success()
    }

    private fun showNotification(itemName: String) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or (if (Build.VERSION.SDK_INT >= 23) PendingIntent.FLAG_IMMUTABLE else 0)
        )

        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle("Hora de repor")
            .setContentText("$itemName está acabando!")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                R.drawable.ic_logo,
                "Adicionar à próxima compra",
                pendingIntent
            )

        NotificationManagerCompat.from(applicationContext).notify(itemName.hashCode(), builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Shopping Reminder",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val KEY_ITEM_NAME = "item_name"
        const val CHANNEL_ID = "shopping_reminder"
    }
}
