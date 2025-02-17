package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.database

import android.content.Context
import android.util.Log
import ru.vlyashuk.androidcoursestepik.R
import javax.inject.Inject

class ExampleDatabase @Inject constructor(
    private val context: Context,
    private val timeMillis: Long
) {

    fun method() {
        Log.i(LOG_TAG, "ExampleDatabase ${context.getString(R.string.dagger2)} $timeMillis $this")
    }

    companion object {
        private const val LOG_TAG = "EXAMPLE_TEST"
    }
}