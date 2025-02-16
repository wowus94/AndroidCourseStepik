package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain

import javax.inject.Inject

class ExampleUseCase @Inject constructor(
    private val repository: ExampleRepository
) {

    operator fun invoke() {
        repository.method()
    }
}