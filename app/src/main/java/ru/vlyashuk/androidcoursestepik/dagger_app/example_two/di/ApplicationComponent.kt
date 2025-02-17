package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, DomainModule::class])
interface ApplicationComponent {

    fun activityComponentFactory(): ActivityComponent.Factory

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context,
            @BindsInstance timeMillis: Long
        ): ApplicationComponent
    }
}