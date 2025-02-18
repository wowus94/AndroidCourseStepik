package ru.vlyashuk.androidcoursestepik.shopping_list.domain

import javax.inject.Inject

class GetShopItemUseCase@Inject constructor(
    private val shopListRepository: ShopListRepository
) {

   suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}