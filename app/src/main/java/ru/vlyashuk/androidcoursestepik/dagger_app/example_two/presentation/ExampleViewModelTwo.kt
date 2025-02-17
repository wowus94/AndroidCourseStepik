package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain.ExampleRepository
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain.ExampleUseCase
import javax.inject.Inject

class ExampleViewModelTwo @Inject constructor(
    private val repository: ExampleRepository
) : ViewModel() {

    fun method() {
        repository.method()
        Log.i("ExampleViewModelTwo", "$this")
    }
}