package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di.IdQualifier
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di.NameQualifier
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain.ExampleUseCase
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val useCase: ExampleUseCase,
    @IdQualifier private val id: String,
    @NameQualifier private val name: String
) : ViewModel() {

    fun method() {
        useCase()
        Log.i("ExampleViewModel", "$id $name" )
    }
}