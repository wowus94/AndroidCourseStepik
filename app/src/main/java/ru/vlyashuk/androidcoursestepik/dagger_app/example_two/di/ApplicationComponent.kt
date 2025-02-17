package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation.ExampleMainActivity

@Component(modules = [DataModule::class, DomainModule::class])
interface ApplicationComponent {

    fun inject(activity: ExampleMainActivity)

    @Component.Builder
    interface ApplicationComponentBuilder {

        @BindsInstance
        fun context(context: Context): ApplicationComponentBuilder

        @BindsInstance
        fun timeMillis(timeMillis: Long): ApplicationComponentBuilder

        fun build(): ApplicationComponent
    }
}