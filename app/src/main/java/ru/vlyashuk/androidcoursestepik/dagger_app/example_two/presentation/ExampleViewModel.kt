package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain.ExampleUseCase
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val useCase: ExampleUseCase,
    private val id: String
) : ViewModel() {

    fun method() {
        useCase()
        Log.i("ExampleViewModel", id)
    }
}