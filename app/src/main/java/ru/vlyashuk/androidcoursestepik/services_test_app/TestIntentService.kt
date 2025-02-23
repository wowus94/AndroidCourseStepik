package ru.vlyashuk.androidcoursestepik.services_test_app

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import ru.vlyashuk.androidcoursestepik.R

class TestIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        startForeground(1, createNotification())
    }

    override fun onHandleIntent(intent: Intent?) {
        for (i in 0 until 5) {
            Thread.sleep(3000)
            println((i + 1).toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun createNotification(): Notification {
        val channelId = "foreground_service_channel"
        val channel = NotificationChannel(
            channelId,
            "Foreground Service Channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Intent Service")
            .setContentText("Сервис работает...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    companion object {
        private const val NAME = "TestIntentService"

        fun newIntent(context: Context): Intent {
            return Intent(context, TestIntentService::class.java)
        }
    }
}
