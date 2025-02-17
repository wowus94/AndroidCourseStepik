package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation.ExampleViewModel
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation.ExampleViewModelTwo

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(ExampleViewModel::class)
    @Binds
    fun bindExampleViewModel(impl: ExampleViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ExampleViewModelTwo::class)
    @Binds
    fun bindExampleViewModelTwo(impl: ExampleViewModelTwo): ViewModel
}