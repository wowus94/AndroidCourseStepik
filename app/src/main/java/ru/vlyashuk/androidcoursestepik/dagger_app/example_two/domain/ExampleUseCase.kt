package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain

class ExampleUseCase(
    private val repository: ExampleRepository
) {

    operator fun invoke() {
        repository.method()
    }
}