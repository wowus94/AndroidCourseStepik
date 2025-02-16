package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import dagger.Binds
import dagger.Module
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleLocalDataSource
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleLocalDataSourceImpl
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleRemoteDataSource
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleRemoteDataSourceImpl

@Module
interface DataModule {

    @Binds
    fun bindLocalDataSource(
        implLocal: ExampleLocalDataSourceImpl
    ): ExampleLocalDataSource

    @Binds
    fun bindRemoteDataSource(
        implRemote: ExampleRemoteDataSourceImpl
    ): ExampleRemoteDataSource
}