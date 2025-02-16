package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.repository

import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleLocalDataSource
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleRemoteDataSource
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.mapper.ExampleMapper
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val localDataSource: ExampleLocalDataSource,
    private val remoteDataSource: ExampleRemoteDataSource,
    private val mapper: ExampleMapper
) : ExampleRepository {

    override fun method() {
        mapper.map()
        localDataSource.method()
        remoteDataSource.method()
    }
}