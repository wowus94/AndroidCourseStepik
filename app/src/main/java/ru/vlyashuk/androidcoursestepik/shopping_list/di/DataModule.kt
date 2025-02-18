package ru.vlyashuk.androidcoursestepik.shopping_list.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.vlyashuk.androidcoursestepik.shopping_list.data.AppDataBaseShoppingList
import ru.vlyashuk.androidcoursestepik.shopping_list.data.ShopListDao
import ru.vlyashuk.androidcoursestepik.shopping_list.data.ShopListRepositoryImpl
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.ShopListRepository

@Module
interface DataModule {
    @ApplicationScopeShoppingList
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @ApplicationScopeShoppingList
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDataBaseShoppingList.getInstance(application).shopListDao()
        }
    }
}