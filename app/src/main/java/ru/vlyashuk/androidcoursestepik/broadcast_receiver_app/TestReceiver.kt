package ru.vlyashuk.androidcoursestepik.broadcast_receiver_app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class TestReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "Низкий заряд батареи", Toast.LENGTH_SHORT).show()
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val stateMode = intent.getBooleanExtra("state", false)
                Toast.makeText(context, "Режим в самолете: $stateMode", Toast.LENGTH_SHORT).show()
            }
        }
    }
}