package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.database

import android.util.Log
import javax.inject.Inject

class ExampleDatabase @Inject constructor() {

    fun method() {
        Log.i(LOG_TAG, "ExampleDatabase")
    }

    companion object {
        private const val LOG_TAG = "EXAMPLE_TEST"
    }
}