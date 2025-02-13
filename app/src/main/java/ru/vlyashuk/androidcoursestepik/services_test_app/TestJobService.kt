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
            var workItem = params?.dequeueWork()
            while (workItem != null) {
                val page = workItem.intent?.getIntExtra(PAGE, 0)
                for (i in 0 until 5) {
                    delay(1000)
                    showToast("Страница: $page #${i + 1} ")
                }
                params?.completeWork(workItem)
                workItem = params?.dequeueWork()
            }
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        println("onStopJob")
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
        private const val PAGE = "page"

        fun newIntent(page: Int): Intent {
            return Intent().apply {
                putExtra(PAGE, page)
            }
        }


    }

}