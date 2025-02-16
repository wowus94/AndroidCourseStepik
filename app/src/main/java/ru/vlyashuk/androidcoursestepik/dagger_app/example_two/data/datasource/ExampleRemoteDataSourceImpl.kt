package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource

import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.network.ExampleApiService
import javax.inject.Inject

class ExampleRemoteDataSourceImpl @Inject constructor(
    private val apiService: ExampleApiService
) : ExampleRemoteDataSource {

    override fun method() {
        apiService.method()
    }
}