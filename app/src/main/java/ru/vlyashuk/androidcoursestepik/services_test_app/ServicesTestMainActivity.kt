package ru.vlyashuk.androidcoursestepik.services_test_app

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.vlyashuk.androidcoursestepik.databinding.ActivityServicesTestMainBinding

class ServicesTestMainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityServicesTestMainBinding.inflate(layoutInflater)
    }

    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            simpleService.setOnClickListener {
                startService(TestBackgroundService.newIntent(this@ServicesTestMainActivity))
            }

            foregroundService.setOnClickListener {
                checkAndRequestNotificationPermission()
            }

        }

    }

    fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // API 33+
            if (ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                startTestForegroundService()
            }
        } else {
            startTestForegroundService()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startTestForegroundService()
            } else {
                Toast.makeText(this, "Разрешение на уведомления необходимо для работы сервиса", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startTestForegroundService() {
        ContextCompat.startForegroundService(
            this@ServicesTestMainActivity,
            Intent(this@ServicesTestMainActivity, TestForegroundService::class.java)
        )
    }

    override fun onStop() {
        super.onStop()
        stopService(TestBackgroundService.newIntent(this))
        stopService(Intent(this@ServicesTestMainActivity, TestForegroundService::class.java))
    }
}