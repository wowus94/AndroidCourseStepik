package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation

import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain.ExampleUseCase
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val useCase: ExampleUseCase
) {

    fun method() {
        useCase()
    }
}