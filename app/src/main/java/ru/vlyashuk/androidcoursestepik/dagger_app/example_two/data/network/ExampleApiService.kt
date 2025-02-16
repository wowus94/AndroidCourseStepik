package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.network

import android.util.Log

class ExampleApiService {

    fun method() {
        Log.d(LOG_TAG, "ExampleApiService")
    }

    companion object {

        private const val LOG_TAG = "EXAMPLE_TEST"
    }
}