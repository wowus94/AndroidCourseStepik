package ru.vlyashuk.androidcoursestepik.shopping_list.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.vlyashuk.androidcoursestepik.shopping_list.data.ShopListRepositoryImpl
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.DeleteShopItemUseCase
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.EditShopItemUseCase
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.GetShopListUseCase
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.ShopItem
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase
) : ViewModel() {

    val shopList = getShopListUseCase.getShopList()

    fun changeEnabledState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }
}