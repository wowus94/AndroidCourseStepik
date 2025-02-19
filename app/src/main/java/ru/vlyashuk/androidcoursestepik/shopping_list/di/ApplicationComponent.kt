package ru.vlyashuk.androidcoursestepik.shopping_list.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.vlyashuk.androidcoursestepik.shopping_list.data.ShopListProvider
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.ShopItemFragment
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.ShoppingListActivity

@ApplicationScopeShoppingList
@Component(
    modules = [
        DataModule::class,
        ViewModelModuleShoppingList::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: ShoppingListActivity)

    fun inject(fragment: ShopItemFragment)

    fun inject(provider: ShopListProvider)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}