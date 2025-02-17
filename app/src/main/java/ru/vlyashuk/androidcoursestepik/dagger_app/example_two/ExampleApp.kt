package ru.vlyashuk.androidcoursestepik.dagger_app.example_two

import android.app.Application
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di.DaggerApplicationComponent

class ExampleApp() : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this, System.currentTimeMillis())
    }

}