package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.datasource

import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.database.ExampleDatabase

class ExampleLocalDataSourceImpl(
    private val database: ExampleDatabase
) : ExampleLocalDataSource {

    override fun method() {
        database.method()
    }
}