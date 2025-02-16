package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import dagger.Component
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation.ExampleMainActivity

@Component(modules = [DataModule::class, DomainModule::class])
interface ApplicationComponent {

    fun inject(activity: ExampleMainActivity)
}