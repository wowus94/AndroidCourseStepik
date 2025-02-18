package ru.vlyashuk.androidcoursestepik.broadcast_receiver_app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import ru.vlyashuk.androidcoursestepik.R

class MainBroadcastReceiverActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    private val testReceiver = TestReceiver()

    private val testReceiverProgress = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "loaded") {
                val percent = intent.getIntExtra("percent", 0)
                progressBar.progress = percent
            }
        }
    }

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_broadcast_receiver)
        progressBar = findViewById(R.id.progressBar)

        findViewById<Button>(R.id.loadBroadcast).setOnClickListener {
            Intent(TestReceiver.ACTION_CLICKED).apply {
                putExtra(TestReceiver.EXTRA_COUNT, ++count)
                sendBroadcast(this)
            }
            Intent(this, TestService::class.java).apply {
                startService(this)
            }
        }

        val intentFilterProgress = IntentFilter().apply {
            addAction("loaded")
        }
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(testReceiverProgress, intentFilterProgress, RECEIVER_EXPORTED)
        registerReceiver(testReceiver, intentFilter, RECEIVER_EXPORTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(testReceiverProgress)
        unregisterReceiver(testReceiver)
    }
}