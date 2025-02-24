package ru.vlyashuk.androidcoursestepik.services_test_app

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import ru.vlyashuk.androidcoursestepik.databinding.ActivityServicesTestMainBinding
import java.util.Calendar

class ServicesTestMainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityServicesTestMainBinding.inflate(layoutInflater)
    }

    private var page = 0

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder) {
            val binder = (service as? TestForegroundService.LocalBinder) ?: return
            val testForegroundService = binder.getService()
            testForegroundService.onProgressChanged = { progress ->
                binding.progressBarLoading.progress = progress
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Toast.makeText(this@ServicesTestMainActivity, "Загрузка завершена", Toast.LENGTH_SHORT)
                .show()
        }

    }

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

            intentService.setOnClickListener {
                ContextCompat.startForegroundService(
                    this@ServicesTestMainActivity,
                    TestIntentService.newIntent(this@ServicesTestMainActivity)
                )
            }

            jobScheduler.setOnClickListener {
                val componentName =
                    ComponentName(this@ServicesTestMainActivity, TestJobService::class.java)

                val jobInfo = JobInfo.Builder(TestJobService.JOB_ID, componentName)
                    .setRequiresCharging(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                    .build()

                val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
                val intent = TestJobService.newIntent(page++)
                jobScheduler.enqueue(jobInfo, JobWorkItem(intent))
            }

            workManager.setOnClickListener {
                val workManager = WorkManager.getInstance(applicationContext)
                workManager.enqueueUniqueWork(
                    TestWorker.WORK_NAME,
                    ExistingWorkPolicy.APPEND,
                    TestWorker.makeRequest(page++)
                )
            }

            alarmManager.setOnClickListener {
                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.SECOND, 20)
                val intent = AlarmReceiver.newIntent(this@ServicesTestMainActivity)
                val pendingIntent =
                    PendingIntent.getBroadcast(
                        this@ServicesTestMainActivity,
                        100,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                alarmManager.setExact(RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            }
        }
    }

    private fun checkAndRequestNotificationPermission() {
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
                Toast.makeText(
                    this,
                    "Разрешение на уведомления необходимо для работы сервиса",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun startTestForegroundService() {
        ContextCompat.startForegroundService(
            this@ServicesTestMainActivity,
            Intent(this@ServicesTestMainActivity, TestForegroundService::class.java)
        )
    }

    override fun onStart() {
        super.onStart()
        bindService(
            TestForegroundService.newIntent(this),
            serviceConnection,
            0
        )
    }

    override fun onStop() {
        super.onStop()
        stopService(TestBackgroundService.newIntent(this))
        stopService(Intent(this@ServicesTestMainActivity, TestForegroundService::class.java))
        unbindService(serviceConnection)
    }

    companion object {

        private const val PERMISSION_REQUEST_CODE = 100

    }
}