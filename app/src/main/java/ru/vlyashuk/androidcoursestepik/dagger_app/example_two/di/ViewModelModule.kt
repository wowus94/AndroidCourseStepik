package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation.ExampleViewModel
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation.ExampleViewModelTwo

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("ExampleViewModel")
    @Binds
    fun bindExampleViewModel(impl: ExampleViewModel): ViewModel

    @IntoMap
    @StringKey("ExampleViewModel2")
    @Binds
    fun bindExampleViewModel2(impl: ExampleViewModelTwo): ViewModel
}