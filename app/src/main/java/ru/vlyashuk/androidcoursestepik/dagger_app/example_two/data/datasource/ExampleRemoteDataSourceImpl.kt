package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource

import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.network.ExampleApiService

class ExampleRemoteDataSourceImpl(
    private val apiService: ExampleApiService
) : ExampleRemoteDataSource {

    override fun method() {
        apiService.method()
    }
}