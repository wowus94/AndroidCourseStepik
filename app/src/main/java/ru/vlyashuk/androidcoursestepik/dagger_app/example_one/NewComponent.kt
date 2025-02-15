package ru.vlyashuk.androidcoursestepik.dagger_app.example_one

import dagger.Component

@Component
interface NewComponent {

    fun getMouse(): Mouse
    fun getMonitor(): Monitor

    fun inject(activity: Activity)
}