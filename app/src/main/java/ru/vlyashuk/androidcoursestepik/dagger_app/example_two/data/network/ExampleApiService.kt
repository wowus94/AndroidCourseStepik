package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.network

import android.content.Context
import android.util.Log
import ru.vlyashuk.androidcoursestepik.R
import javax.inject.Inject

class ExampleApiService @Inject constructor(
    private val context: Context,
    private val timeMillis: Long
) {

    fun method() {
        Log.i(LOG_TAG, "ExampleApiService ${context.getString(R.string.dagger2)} $timeMillis")
    }

    companion object {

        private const val LOG_TAG = "EXAMPLE_TEST"
    }
}