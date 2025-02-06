package ru.vlyashuk.androidcoursestepik.presentation.viewmodels

import androidx.lifecycle.ViewModel
import ru.vlyashuk.androidcoursestepik.data.ShopListRepositoryImpl
import ru.vlyashuk.androidcoursestepik.domain.DeleteShopItemUseCase
import ru.vlyashuk.androidcoursestepik.domain.EditShopItemUseCase
import ru.vlyashuk.androidcoursestepik.domain.GetShopListUseCase
import ru.vlyashuk.androidcoursestepik.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun changeEnabledState(shopItem: ShopItem) {
        editShopItemUseCase.editShopItem(shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        deleteShopItemUseCase.deleteShopItem(newItem)
    }

}