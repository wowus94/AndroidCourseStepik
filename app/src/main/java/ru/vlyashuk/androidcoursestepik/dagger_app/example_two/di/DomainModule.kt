package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di

import dagger.Binds
import dagger.Module
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.data.repository.ExampleRepositoryImpl
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.domain.ExampleRepository

@Module
interface DomainModule {

    @Binds
    fun bindRepository(impl: ExampleRepositoryImpl): ExampleRepository
}