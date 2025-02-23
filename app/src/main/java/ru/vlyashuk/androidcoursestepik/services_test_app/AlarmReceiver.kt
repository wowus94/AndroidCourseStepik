package ru.vlyashuk.androidcoursestepik.services_test_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import ru.vlyashuk.androidcoursestepik.R

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val manager = getSystemService(
                it,
                NotificationManager::class.java
            ) as NotificationManager
            createNotification(manager)

            val notification = NotificationCompat.Builder(
                it,
                CHANNEL_ID
            )
                .setContentTitle("Alarm Receiver")
                .setContentText("Прошло 20 секунд")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()

            manager.notify(NOTIFICATION_ID,notification,)
        }
    }

    private fun createNotification(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }

    companion object {

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_iname"
        private const val NOTIFICATION_ID = 1

        fun newIntent(context: Context): Intent {
            return Intent(context, AlarmReceiver::class.java)
        }
    }
}