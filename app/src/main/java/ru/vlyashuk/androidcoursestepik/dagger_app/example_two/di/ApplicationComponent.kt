package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation.ExampleMainActivity

@ApplicationScope
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: ExampleMainActivity)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
            @BindsInstance timeMillis: Long
        ): ApplicationComponent
    }
}