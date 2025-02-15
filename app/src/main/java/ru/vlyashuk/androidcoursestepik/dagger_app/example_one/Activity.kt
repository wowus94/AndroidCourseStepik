package ru.vlyashuk.androidcoursestepik.dagger_app.example_one

import javax.inject.Inject

class Activity {

    @Inject
    lateinit var computer: Computer

    init {
        DaggerNewComponent.create().inject(this)
    }
}