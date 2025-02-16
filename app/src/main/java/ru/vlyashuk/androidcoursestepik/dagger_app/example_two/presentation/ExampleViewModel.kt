package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation

import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain.ExampleUseCase

class ExampleViewModel(
    private val useCase: ExampleUseCase
) {

    fun method() {
        useCase()
    }
}