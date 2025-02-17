package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@ApplicationScope
class ViewModelFactory @Inject constructor(
    private val viewModelsProvides: @JvmSuppressWildcards Map<String, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    /*override*/ fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelsProvides[modelClass.simpleName]?.get() as T
    }
}