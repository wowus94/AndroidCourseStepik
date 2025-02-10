package ru.vlyashuk.androidcoursestepik.shopping_list.presentation.viewmodels

import androidx.lifecycle.ViewModel
import ru.vlyashuk.androidcoursestepik.shopping_list.data.ShopListRepositoryImpl
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.DeleteShopItemUseCase
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.EditShopItemUseCase
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.GetShopListUseCase
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

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