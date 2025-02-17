package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import dagger.BindsInstance
import dagger.Subcomponent
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation.ExampleMainActivity

@Subcomponent(
    modules = [ViewModelModule::class]
)
interface ActivityComponent {

    fun inject(activity: ExampleMainActivity)

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance id: String
        ): ActivityComponent
    }
}