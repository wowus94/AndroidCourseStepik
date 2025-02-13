package ru.vlyashuk.androidcoursestepik.services_test_app

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestBackgroundService : Service() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        println("onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("onStartCommand")
        scope.launch {
            for (i in 0 until 100) {
                delay(3000)
                showToast((i + 1).toString())
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
        scope.cancel()
    }

    private fun showToast(count: String) {
        Toast.makeText(
            applicationContext,
            "Сервис сработал в фоне: $count", Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, TestBackgroundService::class.java)
        }
    }

}