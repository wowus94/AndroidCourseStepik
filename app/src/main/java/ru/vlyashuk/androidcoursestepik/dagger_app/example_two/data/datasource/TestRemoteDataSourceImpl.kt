package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource

import android.util.Log
import javax.inject.Inject

class TestRemoteDataSourceImpl @Inject constructor() : ExampleRemoteDataSource {

    override fun method() {
        Log.d("TestRemoteDataSource", "test")
    }
}