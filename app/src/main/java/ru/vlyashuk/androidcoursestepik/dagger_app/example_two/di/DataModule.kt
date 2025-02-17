package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import dagger.Binds
import dagger.Module
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleLocalDataSource
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleLocalDataSourceImpl
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleRemoteDataSource
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.ExampleRemoteDataSourceImpl
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource.TestRemoteDataSourceImpl

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindLocalDataSource(
        implLocal: ExampleLocalDataSourceImpl
    ): ExampleLocalDataSource

    @ProdQualifier
    @ApplicationScope
    @Binds
    fun bindRemoteDataSource(
        implRemote: ExampleRemoteDataSourceImpl
    ): ExampleRemoteDataSource

    @TestQualifier
    @ApplicationScope
    @Binds
    fun bindTestRemoteDataSource(
        implRemote: TestRemoteDataSourceImpl
    ): ExampleRemoteDataSource
}