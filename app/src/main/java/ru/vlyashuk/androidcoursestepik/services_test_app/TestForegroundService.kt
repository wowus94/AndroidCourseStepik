package ru.vlyashuk.androidcoursestepik.services_test_app

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vlyashuk.androidcoursestepik.R

class TestForegroundService : Service() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val manager by lazy {
        getSystemService(NotificationManager::class.java)
    }

    override fun onBind(intent: Intent?): IBinder {
        return LocalBinder()
    }

    private val notificationBuilder by lazy {
        createNotificationBuilder()
    }

    var onProgressChanged: ((Int) -> Unit)? = null

    override fun onCreate() {
        super.onCreate()
        startForeground(1, notificationBuilder.build())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scope.launch {
            for (i in 0..100 step 10) {
                delay(1000)
                val notification = notificationBuilder
                    .setProgress(100, i, false)
                    .build()
                manager.notify(1, notification)
                onProgressChanged?.invoke(i)
                println((i + 1).toString())
            }
            stopSelf()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun createNotificationBuilder() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("Foreground Service")
        .setContentText("Сервис работает...")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setProgress(100, 0, false)
        .setOnlyAlertOnce(true)

    inner class LocalBinder : Binder() {
        fun getService() = this@TestForegroundService
    }

    companion object {
        private const val CHANNEL_ID = "foreground_service_channel"

        fun newIntent(context: Context): Intent {
            return Intent(context, TestForegroundService::class.java)
        }
    }

}
