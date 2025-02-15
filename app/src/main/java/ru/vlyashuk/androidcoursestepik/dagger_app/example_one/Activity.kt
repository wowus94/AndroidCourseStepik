package ru.vlyashuk.androidcoursestepik.dagger_app.example_one

import javax.inject.Inject

class Activity {

    @Inject
    lateinit var keyboard: Keyboard

    val monitor: Monitor = DaggerNewComponent.create().getMonitor()
    val mouse: Mouse = DaggerNewComponent.create().getMouse()

    init {
        DaggerNewComponent.create().inject(this)
    }
}