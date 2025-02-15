package ru.vlyashuk.androidcoursestepik.dagger_app.example_one

import dagger.Component

@Component(modules = [ComputerModule::class])
interface NewComponent {

    fun inject(activity: Activity)
}