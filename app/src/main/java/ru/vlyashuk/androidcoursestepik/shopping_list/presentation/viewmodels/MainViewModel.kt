package ru.vlyashuk.androidcoursestepik.shopping_list.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import ru.vlyashuk.androidcoursestepik.shopping_list.data.ShopListRepositoryImpl
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.DeleteShopItemUseCase
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.EditShopItemUseCase
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.GetShopListUseCase
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.ShopItem


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

}