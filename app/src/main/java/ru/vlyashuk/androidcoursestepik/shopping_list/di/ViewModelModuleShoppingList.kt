package ru.vlyashuk.androidcoursestepik.shopping_list.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.viewmodels.MainViewModel
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.viewmodels.ShopItemViewModel

@Module
interface ViewModelModuleShoppingList {

    @Binds
    @IntoMap
    @ViewModelKeyShoppingList(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKeyShoppingList(ShopItemViewModel::class)
    fun bindShopItemViewModel(viewModel: ShopItemViewModel): ViewModel
}