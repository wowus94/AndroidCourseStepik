package ru.vlyashuk.androidcoursestepik.shopping_list.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKeyShoppingList(val value: KClass<out ViewModel>)
