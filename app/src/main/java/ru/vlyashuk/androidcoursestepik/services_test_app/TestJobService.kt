package ru.vlyashuk.androidcoursestepik.services_test_app

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestJobService : JobService() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        println("onCreate()")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        scope.launch {
            for (i in 0 until 5) {
                delay(2000)
                showToast((i + 1).toString())
            }
            jobFinished(params, true)
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    private fun showToast(count: String) {
        Toast.makeText(
            applicationContext,
            "Сервис сработал: $count", Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
        scope.cancel()
    }

    companion object {

        const val JOB_ID = 101
    }

}