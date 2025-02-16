package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.network

import android.util.Log
import javax.inject.Inject

class ExampleApiService @Inject constructor() {

    fun method() {
        Log.i(LOG_TAG, "ExampleApiService")
    }

    companion object {

        private const val LOG_TAG = "EXAMPLE_TEST"
    }
}